package desktop.hambug.domain.usecase

import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class CreateBoardUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(title: String, content: String, category: String): Result<Int> {
        return runCatching {
            repository.createBoard(title, content, category)
        }
    }
}
