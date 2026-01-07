package desktop.hambug.domain.usecase.fcm

import android.util.Log
import desktop.hambug.data.local.HambugTokenManager
import javax.inject.Inject

class SyncFcmTokenUseCase @Inject constructor(
    private val getFcmTokenUseCase: GetFcmTokenUseCase,
    private val updateFcmTokenUseCase: UpdateFcmTokenUseCase,
    private val tokenManager: HambugTokenManager
) {
    suspend operator fun invoke(): Result<Unit> = runCatching {
        if (!tokenManager.isLogin()) {
            Log.d("fcm", "로그인 상태 아님, FCM 토큰 동기화 패스")
            return@runCatching
        }

        // Firebase에서 FCM 토큰 조회
        val currentToken = getFcmTokenUseCase().getOrThrow()

        // 로컬에 저장된 FCM 토큰 조회
        val savedToken = tokenManager.getFcmToken()

        // 토큰이 다르면 서버에 업데이트
        if (currentToken != savedToken) {
            Log.d("fcm", "FCM 토큰이 변경되어 서버 업데이트 시작")
            updateFcmTokenUseCase(currentToken).getOrThrow()
            tokenManager.saveFcmToken(currentToken)
            Log.d("fcm", "FCM 토큰 동기화 완료")
        } else {
            Log.d("fcm", "FCM 토큰이 동일하여 서버 업데이트 패스")
        }
    }
}
