package desktop.hambug.domain.usecase.fcm

import desktop.hambug.data.local.HambugTokenManager
import javax.inject.Inject

class SyncFcmTokenUseCase @Inject constructor(
    private val getFcmTokenUseCase: GetFcmTokenUseCase,
    private val updateFcmTokenUseCase: UpdateFcmTokenUseCase,
    private val tokenManager: HambugTokenManager
) {
    suspend operator fun invoke(): Result<Unit> = runCatching {
        if (!tokenManager.isLogin()) return@runCatching

        // Firebase에서 FCM 토큰 조회
        val currentToken = getFcmTokenUseCase().getOrThrow()

        // 로컬에 저장된 FCM 토큰 조회
        val savedToken = tokenManager.getFcmToken()

        // 토큰이 다르면 서버에 업데이트
        if (currentToken != savedToken) {
            updateFcmTokenUseCase(currentToken).getOrThrow()
            tokenManager.saveFcmToken(currentToken)
        }
    }
}
