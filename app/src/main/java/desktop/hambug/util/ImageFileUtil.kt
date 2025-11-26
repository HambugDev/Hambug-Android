package desktop.hambug.util

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
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
