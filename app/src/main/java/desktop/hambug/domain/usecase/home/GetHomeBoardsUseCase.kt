package desktop.hambug.domain.usecase.home

import desktop.hambug.domain.model.HomeBoard
import desktop.hambug.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeBoardsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<HomeBoard>> {
        return runCatching {
            repository.getHomeBoards()
        }
    }
}
