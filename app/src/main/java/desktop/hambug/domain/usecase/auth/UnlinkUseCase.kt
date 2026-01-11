package desktop.hambug.domain.usecase.auth

import android.util.Log
import desktop.hambug.data.local.HambugTokenManager
import desktop.hambug.domain.repository.AuthRepository
import javax.inject.Inject

class UnlinkUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: HambugTokenManager
) {
    suspend operator fun invoke(provider: String): Result<Unit> {
        return runCatching {
            Log.d("auth", "회원탈퇴 시작: provider=$provider")
            repository.unlink(provider)   // 실패 시 예외 발생
            tokenManager.clearAllToken()  // 성공 시에만 실행
        }.onFailure { exception ->
            Log.e("auth", "회원탈퇴 실패: ${exception.message}", exception)
        }
    }
}
