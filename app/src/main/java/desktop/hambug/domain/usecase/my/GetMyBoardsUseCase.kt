package desktop.hambug.domain.usecase.my

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
