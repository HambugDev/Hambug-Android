package desktop.hambug.domain.usecase

import desktop.hambug.domain.model.BoardDetail
import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class GetBoardDetailUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(boardId: Int): Result<BoardDetail> {
        return runCatching {
            repository.getBoardDetail(boardId)
        }
    }
}
