package desktop.hambug.domain.usecase

import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class DeleteCommentUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(boardId: Int, commentId: Int): Result<Unit> {
        return runCatching {
            repository.deleteComment(boardId, commentId)
        }
    }
}
