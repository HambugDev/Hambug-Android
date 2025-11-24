package desktop.hambug.data.repository

import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.mapper.toEntity
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

        return response.data.map { it.toEntity() }
    }
}
