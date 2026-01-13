package desktop.hambug.domain.usecase.auth

import desktop.hambug.data.local.HambugTokenManager
import desktop.hambug.domain.repository.AuthRepository
import javax.inject.Inject

class UnlinkUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: HambugTokenManager
) {
    suspend operator fun invoke(provider: String): Result<Unit> {
        return runCatching {
            repository.unlink(provider)   // 실패 시 예외 발생
            tokenManager.clearAllToken()  // 성공 시에만 실행
        }
    }
}
