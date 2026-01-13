package desktop.hambug.domain.usecase.my

import desktop.hambug.domain.model.MyComment
import desktop.hambug.domain.repository.MyRepository
import javax.inject.Inject

class GetMyCommentsUseCase @Inject constructor(
    private val repository: MyRepository
) {
    suspend operator fun invoke(): Result<List<MyComment>> {
        return runCatching {
            repository.getMyComments()
        }
    }
}
