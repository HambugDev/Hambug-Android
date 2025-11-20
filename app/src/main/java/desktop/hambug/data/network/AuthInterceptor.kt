package desktop.hambug.data.network

import desktop.hambug.data.local.HambugTokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: HambugTokenManager
) : Interceptor {

    // 토큰이 필요없는 API
    private val NO_AUTH_PATH = setOf(
        "auth/login/kakao"
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestPath = originalRequest.url.encodedPath

        // 토큰이 필요없는 API 처리
        if (NO_AUTH_PATH.any { path -> requestPath.contains(path) }) {
            return chain.proceed(originalRequest)
        }

        val accessToken = runBlocking {
            tokenManager.getAccessToken()
        }

        val newRequest = if (accessToken != null) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
        } else {
            // 토큰이 없으면 기존 요청 사용
            originalRequest
        }

        return chain.proceed(newRequest)
    }
}
