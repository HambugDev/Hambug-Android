package desktop.hambug.domain.repository

import desktop.hambug.domain.model.Burger

interface BurgerRepository {
    suspend fun getBurgerList(): List<Burger>
}
