package desktop.hambug.data.network

import android.util.Log
import desktop.hambug.data.api.RefreshApi
import desktop.hambug.data.local.HambugTokenManager
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit

class TokenAuthenticator(
    private val tokenManager: HambugTokenManager,
    private val refreshRetrofit: Retrofit
) : Authenticator {

    private val tokenRefreshMutex = Mutex()

    private val refreshApi = refreshRetrofit.create(RefreshApi::class.java)

    override fun authenticate(route: Route?, response: Response): Request? {

        Log.d("auth", "HTTP 메서드: ${response.code}")

        // HTTP 401일 때만 실행
        if (response.code != 401) {
            return null
        }

        // 1차 체크 (Mutex 진입 전)
        // 갱신이 완료된 후 진입하는 요청 처리 (새 토큰으로 재시도 시킴)
        val currentAccessToken = runBlocking { tokenManager.getAccessToken() }
        // 현재 요청의 토큰이 최신 토큰과 다르면, 이미 다른 요청에 의해 토큰 갱신된 상태
        if (response.request.header("Authorization") != "Bearer $currentAccessToken") {
            Log.d("auth", "토큰 이미 갱신됨. 새 토큰으로 재시도")
            return response.request.newBuilder()
                .header("Authorization", "Bearer $currentAccessToken")
                .build()
        }

        return runBlocking {
            val oldRefreshToken = tokenManager.getRefreshToken()

            if (oldRefreshToken == null) {
                Log.e("auth", "refresh token 없음")
                tokenManager.logout()
                return@runBlocking null
            }

            // Mutex 락을 사용하여 동시 요청 방지
            val newAccessToken = tokenRefreshMutex.withLock {
                val currentAccessTokenInLock = tokenManager.getAccessToken()

                // 2차 체크 (Mutex 락 내부)
                // 갱신이 진행되는 동안 대기했던 요청이 중복 호출 되지 않도록 확인
                if (response.request.header("Authorization") != "Bearer $currentAccessTokenInLock") {
                    Log.d("auth", "토큰 이미 갱신됨. 새 토큰 반환")
                    return@withLock currentAccessTokenInLock
                }

                // 토큰 갱신 API 호출
                try {
                    val refreshResponse = refreshApi.refreshToken("Bearer $oldRefreshToken")

                    if (refreshResponse.success) {
                        val newToken = refreshResponse.data
                        tokenManager.saveAccessToken(newToken)
                        Log.d("auth", "access token 갱신 성공")
                        newToken
                    } else {
                        Log.e("auth", "refresh token 만료 or 갱신 실패: ${refreshResponse.message}")
                        tokenManager.logout()
                        null
                    }
                } catch (e: Exception) {
                    Log.e("auth", "refresh API 호출 중 예외발생", e)
                    tokenManager.logout()
                    null
                }
            }

            // 갱신 성공 시, 원래 요청을 새로운 access token으로 재시도
            return@runBlocking if (newAccessToken != null) {
                response.request.newBuilder()
                    .header("Authorization", "Bearer $newAccessToken")
                    .build()
            } else {
                null
            }
        }
    }
}
