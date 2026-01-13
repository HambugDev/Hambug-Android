package desktop.hambug.domain.usecase.my

import desktop.hambug.domain.model.UserInfo
import desktop.hambug.domain.repository.MyRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: MyRepository
) {
    suspend operator fun invoke(): Result<UserInfo> {
        return runCatching {
            repository.getUserInfo()
        }
    }
}
