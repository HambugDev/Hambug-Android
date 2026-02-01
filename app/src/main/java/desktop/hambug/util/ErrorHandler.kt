package desktop.hambug.util

import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException

interface ErrorHandler {
    fun getErrorMessage(throwable: Throwable): String
}

class ErrorHandlerImpl : ErrorHandler {
    override fun getErrorMessage(throwable: Throwable): String {
        Timber.e(throwable, "error occurred")

        // 유저용 메시지 반환
        return when (throwable) {
            is HttpException -> {
                Timber.e("error code : ${throwable.code()}")
                when (throwable.code()) {
                    in 500..511 -> "일시적인 오류가 발생했어요"
                    else -> "오류가 발생했어요"
                }
            }
            is SocketTimeoutException -> "일시적인 오류가 발생했어요"
            else -> "오류가 발생했어요"
        }
    }
}
