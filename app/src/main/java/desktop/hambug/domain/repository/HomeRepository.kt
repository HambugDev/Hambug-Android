package desktop.hambug.domain.repository

import desktop.hambug.domain.model.HomeBurger

interface HomeRepository {
    suspend fun getHomeBurgers(): List<HomeBurger>
}
