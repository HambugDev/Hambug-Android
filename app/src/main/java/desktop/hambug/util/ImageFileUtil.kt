package desktop.hambug.util

import android.content.Context
import android.net.Uri
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream

fun createMultipartBodyPart(
    context: Context,
    fileUri: Uri,
    partName: String
) : Pair<MultipartBody.Part, File>? {

    val contentResolver = context.contentResolver
    val mimeType = contentResolver.getType(fileUri) ?: "image/*"

    // 파일 이름 생성
    val displayName = "profile_upload_${System.currentTimeMillis()}"
    // 임시 파일 경로
    val tempFile = File(context.cacheDir, displayName)

    return try {
        // use를 사용하면 자동으로 스트림이 닫힘
        contentResolver.openInputStream(fileUri)?.use { inputStream ->
            FileOutputStream(tempFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        } ?: return null

        // RequestBody 생성
        val requestBody = tempFile.asRequestBody(mimeType.toMediaTypeOrNull())
        // MultipartBody.Part 생성
        val part = MultipartBody.Part.createFormData(partName, displayName, requestBody)

        Pair(part, tempFile)
    } catch (e: Exception) {
        e.printStackTrace()
        tempFile.delete()
        null
    }
}

fun createMultipartBodyParts(
    context: Context,
    fileUris: List<Uri>,
    partName: String = "images"
): List<MultipartBody.Part> {
    return fileUris.mapNotNull { uri ->
        createMultipartBodyPartFromUri(context, uri, partName)
    }
}

private fun createMultipartBodyPartFromUri(
    context: Context,
    fileUri: Uri,
    partName: String
): MultipartBody.Part? {
    return try {
        val contentResolver = context.contentResolver
        val mimeType = contentResolver.getType(fileUri) ?: "image/*"

        // 바이트 배열로 메모리에 읽기
        val bytes = contentResolver.openInputStream(fileUri)?.use { inputStream ->
            inputStream.readBytes()
        } ?: run {
            return null
        }

        // 고유한 파일명 생성
        val fileName = createFileName(fileUri)
        // RequestBody 생성
        val requestBody = bytes.toRequestBody(mimeType.toMediaTypeOrNull())
        // MultipartBody.Part 생성
        MultipartBody.Part.createFormData(partName, fileName, requestBody)
    } catch (e: Exception) {
        Log.e("community", "createMultipartBodyPartFromUri 실패: ${e.message}", e)
        null
    }
}

private fun createFileName(uri: Uri): String {
    val timestamp = System.currentTimeMillis()
    val uniqueId = uri.hashCode().toString().takeLast(4)
    return "image_${timestamp}_$uniqueId"
}
