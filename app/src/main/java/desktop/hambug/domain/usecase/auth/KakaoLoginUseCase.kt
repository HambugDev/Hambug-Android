package desktop.hambug.domain.usecase.auth

import android.content.Context
import desktop.hambug.data.dto.auth.LoginResponse
import desktop.hambug.domain.repository.AuthRepository
import javax.inject.Inject

class KakaoLoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(context: Context): Result<LoginResponse> {
        return runCatching {
            repository.login(context)
        }
    }
}
