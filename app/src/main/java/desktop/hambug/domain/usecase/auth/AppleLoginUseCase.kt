package desktop.hambug.domain.usecase.auth

import desktop.hambug.data.dto.auth.LoginResponse
import desktop.hambug.domain.repository.AuthRepository
import javax.inject.Inject

class AppleLoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(identityToken: String): Result<LoginResponse> {
        return runCatching {
            repository.loginWithApple(identityToken)
        }
    }
}
