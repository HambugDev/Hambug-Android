package desktop.hambug.domain.usecase

import desktop.hambug.domain.model.MyBoard
import desktop.hambug.domain.repository.MyRepository
import javax.inject.Inject

class GetMyBoardsUseCase @Inject constructor(
    private val repository: MyRepository
) {
    suspend operator fun invoke(): Result<List<MyBoard>> {
        return runCatching {
            repository.getMyBoards()
        }
    }
}
