package desktop.hambug.domain.usecase.home

import desktop.hambug.domain.model.HomeBurger
import desktop.hambug.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeBurgersUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<HomeBurger>> {
        return runCatching {
            repository.getHomeBurgers()
        }
    }
}
