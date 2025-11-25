package desktop.hambug.data.repository

import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.dto.NicknameUpdateRequest
import desktop.hambug.data.mapper.toEntity
import desktop.hambug.domain.model.UserInfo
import desktop.hambug.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val hambugApi: HambugApi
) : MyRepository {
    override suspend fun getUserInfo(): UserInfo {
        val response = hambugApi.getUserInfo()

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.toEntity()
    }

    override suspend fun updateUserNickname(userId: Int, nickname: String): UserInfo {
        val request = NicknameUpdateRequest(nickname)
        val response = hambugApi.putUserNickname(id = userId, request = request)

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.toEntity()
    }
}
