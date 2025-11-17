package desktop.hambug.data.repository

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import desktop.hambug.domain.repository.KakaoLoginRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class KakaoLoginRepositoryImpl @Inject constructor() : KakaoLoginRepository {

    override suspend fun login(context: Context): String = suspendCancellableCoroutine { continuation ->

        // UserApiClient 콜백 정의
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->

            if (error != null) {
                Log.e("auth", "카카오 로그인 실패", error)
                if (continuation.isActive) {
                    continuation.resumeWith(Result.failure(error))
                }
            } else if (token != null) {
                Log.d("auth", "카카오 access token: ${token.accessToken}")
                if (continuation.isActive) {
                    continuation.resumeWith(Result.success(token.accessToken))
                }
            }
        }

        // 카카오톡 설치 여부에 따른 분기 처리
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            // 카카오톡 앱을 통한 로그인 요청
            UserApiClient.instance.loginWithKakaoTalk(
                context = context,
                callback = callback
            )
        } else {
            // 카카오 계정 웹뷰를 통한 로그인 요청
            UserApiClient.instance.loginWithKakaoAccount(
                context = context,
                callback = callback
            )
        }
    }
}
