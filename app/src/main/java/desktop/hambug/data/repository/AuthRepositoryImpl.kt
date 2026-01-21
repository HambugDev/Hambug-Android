package desktop.hambug.data.repository

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.dto.auth.LoginResponse
import desktop.hambug.data.dto.auth.LoginRequest
import desktop.hambug.data.local.HambugTokenManager
import desktop.hambug.domain.repository.AuthRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val hambugApi: HambugApi,
    private val tokenManager: HambugTokenManager
) : AuthRepository {

    override suspend fun login(context: Context): LoginResponse {

        // 카카오 accessToken 획득
        val kakaoAccessToken = suspendCancellableCoroutine { continuation ->
            // UserApiClient 콜백 정의
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Timber.e(error, "카카오 로그인 실패")
                    if (continuation.isActive) {
                        continuation.resumeWith(Result.failure(error))
                    }
                } else if (token != null) {
                    Timber.d("카카오 access token: ${token.accessToken}")
                    if (continuation.isActive) {
                        continuation.resumeWith(Result.success(token.accessToken))
                    }
                }
            }

            // 카카오톡 설치 여부에 따른 분기 처리
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                // 카카오톡 앱을 통한 로그인 요청
                UserApiClient.instance.loginWithKakaoTalk(context = context, callback = callback)
            } else {
                // 카카오 계정 웹뷰를 통한 로그인 요청
                UserApiClient.instance.loginWithKakaoAccount(context = context, callback = callback)
            }
        }

        // 로그인 API 호출
        val request = LoginRequest(accessToken = kakaoAccessToken)
        val response = hambugApi.login(provider = "kakao", request = request)

        // 응답 처리
        if (response.success) {
            val loginData = response.data ?: throw Exception("로그인 성공했지만 데이터 없음")

            val accessToken = loginData.token?.accessToken
            val refreshToken = loginData.token?.refreshToken

            if (accessToken.isNullOrBlank() || refreshToken.isNullOrBlank()) {
                throw Exception("서버 응답 성공했지만 인증 토큰 누락")
            }

            tokenManager.saveTokens(accessToken, refreshToken)

            return loginData
        } else {
            throw Exception("서버 로그인 실패: ${response.message}")
        }
    }

    override suspend fun logout() {
        val response = hambugApi.logout()

        if (!response.success) {
            throw Exception(response.message)
        }
    }

    override suspend fun unlink(provider: String) {
        val response = hambugApi.unlink(provider.lowercase())

        if (!response.success) {
            throw Exception(response.message)
        }
    }
}
