package desktop.hambug.domain.usecase

import desktop.hambug.domain.model.Comment
import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(boardId: Int): Result<List<Comment>> {
        return runCatching {
            repository.getComments(boardId)
        }
    }
}
