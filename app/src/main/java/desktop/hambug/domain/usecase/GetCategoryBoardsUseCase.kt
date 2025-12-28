package desktop.hambug.domain.usecase

import desktop.hambug.domain.model.BoardPage
import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class GetCategoryBoardsUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(category: String, lastId: Int? = null): Result<BoardPage> {
        return runCatching {
            repository.getCategoryBoards(category, lastId)
        }
    }
}
