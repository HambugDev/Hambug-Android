package desktop.hambug.data.repository

import desktop.hambug.domain.model.Burger
import desktop.hambug.domain.repository.BurgerRepository
import javax.inject.Inject

class BurgerRepositoryImpl @Inject constructor() : BurgerRepository {
    override suspend fun getBurgerList(): List<Burger> {
        return listOf(
            Burger(name = "코울슬로치킨", description = "코울슬로, 바삭치킨의 달콤한 어울림"),
            Burger(name = "징거타워", description = "치킨, 해시브라운, 치즈의 든든함")
        )
    }
}
