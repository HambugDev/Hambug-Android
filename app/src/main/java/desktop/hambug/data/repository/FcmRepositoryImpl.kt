package desktop.hambug.data.repository

import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.dto.fcm.FcmTokenRequest
import desktop.hambug.data.mapper.toEntity
import desktop.hambug.domain.model.Noti
import desktop.hambug.domain.repository.FcmRepository
import javax.inject.Inject

class FcmRepositoryImpl @Inject constructor(
    private val hambugApi: HambugApi
) : FcmRepository {
    override suspend fun updateFcmToken(token: String) {
        val response = hambugApi.updateFcmToken(
            request = FcmTokenRequest(token, "android")
        )

        if (!response.success) {
            throw Exception(response.message)
        }
    }

    override suspend fun getNotis(): List<Noti> {
        val response = hambugApi.getNotis()

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.content.map { it.toEntity() }
    }
}
