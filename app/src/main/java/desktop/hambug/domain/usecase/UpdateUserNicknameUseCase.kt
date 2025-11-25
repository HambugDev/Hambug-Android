package desktop.hambug.domain.usecase

import desktop.hambug.domain.model.UserInfo
import desktop.hambug.domain.repository.MyRepository
import javax.inject.Inject

class UpdateUserNicknameUseCase @Inject constructor(
    private val repository: MyRepository
) {
    suspend operator fun invoke(userId: Int, nickname: String): Result<UserInfo> {
        return runCatching {
            repository.updateUserNickname(userId = userId, nickname = nickname)
        }
    }
}
