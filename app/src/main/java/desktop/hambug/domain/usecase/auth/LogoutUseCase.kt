package desktop.hambug.domain.usecase.auth

import desktop.hambug.data.local.HambugTokenManager
import desktop.hambug.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: HambugTokenManager
) {
    suspend operator fun invoke(): Result<Unit> {
        val serverResult = runCatching {
            repository.logout()
        }

        // 결과와 상관없이 로컬 토큰 제거
        tokenManager.logout()

        return serverResult
    }
}
