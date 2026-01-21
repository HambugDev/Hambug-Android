package desktop.hambug.data.repository

import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.mapper.toEntity
import desktop.hambug.domain.model.HomeBoard
import desktop.hambug.domain.model.HomeBurger
import desktop.hambug.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val hambugApi: HambugApi
) : HomeRepository {
    override suspend fun getHomeBurgers(): List<HomeBurger> {
        val response = hambugApi.getHomeBurgers()

        // 실패 처리
        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data?.map { it.toEntity() } ?: emptyList()
    }

    override suspend fun getHomeBoards(): List<HomeBoard> {
        val response = hambugApi.getHomeBoards()

        if (!response.success) {
            throw Exception(response.message)
        }

        // mapNotNull을 사용하여 id가 없는 아이템 제거
        return response.data?.mapNotNull { it.toEntity() } ?: emptyList()
    }
}
