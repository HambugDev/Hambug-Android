package desktop.hambug.domain.repository

import desktop.hambug.domain.model.HomeBoard
import desktop.hambug.domain.model.HomeBurger

interface HomeRepository {
    suspend fun getHomeBurgers(): List<HomeBurger>
    suspend fun getHomeBoards(): List<HomeBoard>
}
