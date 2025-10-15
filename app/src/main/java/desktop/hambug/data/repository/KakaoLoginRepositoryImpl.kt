package desktop.hambug.data.repository

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.AuthCodeClient
import com.kakao.sdk.user.UserApiClient
import desktop.hambug.domain.repository.KakaoLoginRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class KakaoLoginRepositoryImpl @Inject constructor() : KakaoLoginRepository {

    override suspend fun login(context: Context): String = suspendCancellableCoroutine { continuation ->

        // AuthCodeClient 콜백 정의
        // -> 성공 시 String 형태의 인가 코드를 받음
        val callback: (String?, Throwable?) -> Unit = { authCode, error ->

            if (error != null) {
                // 실패
                Log.e("kakao", "인가 코드 획득 실패", error)
                if (continuation.isActive) {
                    continuation.resumeWith(Result.failure(error))
                }
            } else if (authCode != null) {
                // 성공
                Log.d("kakao", "인가 코드 획득 성공: $authCode")
                if (continuation.isActive) {
                    continuation.resumeWith(Result.success(authCode))
                }
            }
        }

        // 카카오톡 설치 여부에 따른 분기 처리
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            // 카카오톡 설치: 카카오톡 앱을 통한 인가 코드 요청
            AuthCodeClient.instance.authorizeWithKakaoTalk(
                context = context,
                callback = callback
            )
        } else {
            // 카카오톡 미설치: 카카오 계정 웹뷰를 통한 인가 코드 요청
            AuthCodeClient.instance.authorizeWithKakaoAccount(
                context = context,
                callback = callback
            )
        }

        // 코루틴 취소 시 처리
        continuation.invokeOnCancellation {
            Log.d("kakao", "로그인 시도 취소됨")
        }
    }
}
