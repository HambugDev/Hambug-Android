package desktop.hambug.domain.usecase

import desktop.hambug.domain.model.Board
import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class GetBoardsUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(): Result<List<Board>> {
        return runCatching {
            repository.getBoards()
        }
    }
}
