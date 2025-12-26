package desktop.hambug.util

import android.content.Context
import android.net.Uri
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

object ImageFileUtil {
    /**
     * 단일 이미지를 MultipartBody.Part로 변환
     */
    fun createMultipartBodyPart(
        context: Context,
        fileUri: Uri,
        partName: String
    ): MultipartBody.Part? {
        return createMultipartBodyPartFromUri(context, fileUri, partName)
    }

    /**
     * 여러 이미지를 MultipartBody.Part 리스트로 변환
     */
    fun createMultipartBodyParts(
        context: Context,
        fileUris: List<Uri>,
        partName: String = "images"
    ): List<MultipartBody.Part> {
        return fileUris.mapNotNull { uri ->
            createMultipartBodyPartFromUri(context, uri, partName)
        }
    }

    /**
     * Uri를 MultipartBody.Part로 변환하는 공통 로직
     */
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
}
