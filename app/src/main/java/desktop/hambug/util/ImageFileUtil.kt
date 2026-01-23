package desktop.hambug.util

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber

object ImageFileUtil {
    /**
     * 단일 이미지를 MultipartBody.Part로 변환
     */
    suspend fun createMultipartBodyPart(
        context: Context,
        fileUri: Uri,
        partName: String
    ): MultipartBody.Part? {
        return createMultipartBodyPartFromUri(context, fileUri, partName)
    }

    /**
     * 여러 이미지를 MultipartBody.Part 리스트로 변환
     */
    suspend fun createMultipartBodyParts(
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
    private suspend fun createMultipartBodyPartFromUri(
        context: Context,
        fileUri: Uri,
        partName: String
    ): MultipartBody.Part? {
        return try {
            val result = ImageCompressor.compress(context, fileUri)

            // 고유한 파일명 생성
            val fileName = createFileName(fileUri)

            // RequestBody 생성
            val requestBody = result.toRequestBody("image/jpeg".toMediaTypeOrNull())

            // MultipartBody.Part 생성
            MultipartBody.Part.createFormData(partName, fileName, requestBody)
        } catch (e: Exception) {
            Timber.e(e, "createMultipartBodyPartFromUri 실패")
            null
        }
    }

    private fun createFileName(uri: Uri): String {
        val timestamp = System.currentTimeMillis()
        val uniqueId = uri.hashCode().toString().takeLast(4)
        return "image_${timestamp}_$uniqueId.jpg"
    }
}
