package desktop.hambug.domain.usecase.community

import desktop.hambug.data.dto.community.LikeBoardResponse
import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class LikeBoardUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(boardId: Int): Result<LikeBoardResponse> {
        return runCatching {
            repository.likeBoard(boardId)
        }
    }
}
