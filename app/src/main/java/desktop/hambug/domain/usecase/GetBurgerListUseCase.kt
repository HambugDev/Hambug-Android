package desktop.hambug.domain.usecase

import desktop.hambug.domain.model.Burger
import desktop.hambug.domain.repository.BurgerRepository
import javax.inject.Inject

class GetBurgerListUseCase @Inject constructor(
    private val repository: BurgerRepository
) {
    suspend operator fun invoke(): List<Burger> {
        return repository.getBurgerList()
    }
}
