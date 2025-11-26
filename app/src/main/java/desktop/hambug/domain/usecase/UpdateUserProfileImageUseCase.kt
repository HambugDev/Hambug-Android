package desktop.hambug.domain.usecase

import android.net.Uri
import desktop.hambug.domain.model.UserInfo
import desktop.hambug.domain.repository.MyRepository
import javax.inject.Inject

class UpdateUserProfileImageUseCase @Inject constructor(
    private val repository: MyRepository
) {
    suspend operator fun invoke(userId: Int, imageUri: Uri): Result<UserInfo> {
        return runCatching {
            repository.updateUserProfileImage(userId = userId, imageUri = imageUri)
        }
    }
}
