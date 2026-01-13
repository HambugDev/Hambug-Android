package desktop.hambug.domain.usecase.community

import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class DeleteBoardUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(boardId: Int): Result<Unit> {
        return runCatching {
            repository.deleteBoard(boardId)
        }
    }
}
