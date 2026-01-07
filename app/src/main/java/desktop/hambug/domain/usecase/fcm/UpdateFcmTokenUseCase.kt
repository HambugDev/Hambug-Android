package desktop.hambug.domain.usecase.fcm

import desktop.hambug.domain.repository.FcmRepository
import javax.inject.Inject

class UpdateFcmTokenUseCase @Inject constructor(
    private val repository: FcmRepository
) {
    suspend operator fun invoke(token: String): Result<Unit> {
        return runCatching {
            repository.updateFcmToken(token)
        }
    }
}
