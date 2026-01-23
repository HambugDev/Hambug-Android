package desktop.hambug.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import androidx.core.graphics.scale
import java.io.ByteArrayOutputStream

object ImageCompressor {
    private const val MAX_DIMENSION = 1920  // 긴 변 최대 길이
    private const val JPG_QUALITY = 85      // JPG 압축 품질 (0~100)

    /**
     * 이미지 처리 (리사이징 + 압축)
     * @return 처리된 이미지 바이트 배열
     */
    suspend fun compress(context: Context, uri: Uri): ByteArray = withContext(Dispatchers.IO) {
        val startTime = System.currentTimeMillis()

        // 원본 크기 확인
        val oldSize = getFileSize(context, uri)
        Timber.d("이미지 원본 크기: ${formatSize(oldSize)}")

        // 비트맵으로 디코딩
        val bitmap = context.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it)
        } ?: throw Exception("이미지를 읽을 수 없습니다")

        Timber.d("이미지 원본 해상도: ${bitmap.width} x ${bitmap.height}")

        try {
            val resized = resize(bitmap)

            if (resized != bitmap) {
                Timber.d("이미지 리사이징 완료: ${resized.width} x ${resized.height}")
            } else {
                Timber.d("이미지 리사이징 불필요 (이미 작음)")
            }

            // JPG로 압축
            val compressedBytes = ByteArrayOutputStream().use { out ->
                resized.compress(Bitmap.CompressFormat.JPEG, JPG_QUALITY, out)
                resized.recycle()  // 메모리 해제
                out.toByteArray()
            }

            // 로그 출력
            val compressedSize = compressedBytes.size.toLong()
            val processingTime = System.currentTimeMillis() - startTime

            Timber.d("이미지 처리 후 크기: ${formatSize(compressedSize)}")
            Timber.d("이미지 처리 시간: ${processingTime}ms")

            compressedBytes
        } finally {
            bitmap.recycle()  // 메모리 해제
        }
    }

    /**
     * 비트맵 리사이징 (긴 변 기준)
     */
    private fun resize(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val maxDimension = maxOf(width, height)

        // 이미지 작으면 반환
        if (maxDimension <= MAX_DIMENSION) return bitmap

        // 비율 계산
        val scale = MAX_DIMENSION.toFloat() / maxDimension
        val newWidth = (width * scale).toInt()
        val newHeight = (height * scale).toInt()

        return bitmap.scale(newWidth, newHeight).also {
            // 새 비트맵이 생성되면 기존 메모리 해제
            if (it != bitmap) bitmap.recycle()
        }
    }

    /**
     * 파일 크기 가져오기 (로그용)
     */
    private fun getFileSize(context: Context, uri: Uri): Long {
        return try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                inputStream.available().toLong()
            } ?: 0L
        } catch (e: Exception) {
            Timber.e(e, "파일 크기 확인 실패")
            0L
        }
    }

    /**
     * 바이트를 읽기 편한 형식으로 변환
     * - 512 -> 512B
     * - 1536 -> 1.50KB
     * - 6291456 -> 6.00MB
     */
    @SuppressLint("DefaultLocale")
    private fun formatSize(bytes: Long): String {
        return when {
            bytes < 1024 -> "${bytes}B"
            bytes < 1024 * 1024 -> String.format("%.2fKB", bytes / 1024.0)
            else -> String.format("%.2fMB", bytes / (1024.0 * 1024.0))
        }
    }
}
