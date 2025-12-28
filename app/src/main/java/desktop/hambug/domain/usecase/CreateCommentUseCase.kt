package desktop.hambug.domain.usecase

import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class CreateCommentUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(boardId: Int, comment: String): Result<Unit> {
        return runCatching {
            repository.createComment(boardId, comment)
        }
    }
}
