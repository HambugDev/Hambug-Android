package desktop.hambug.domain.usecase.community

import desktop.hambug.data.dto.community.LikeBoardData
import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class LikeBoardUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(boardId: Int): Result<LikeBoardData> {
        return runCatching {
            repository.likeBoard(boardId)
        }
    }
}
