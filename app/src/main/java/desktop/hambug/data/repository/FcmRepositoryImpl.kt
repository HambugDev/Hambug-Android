package desktop.hambug.data.repository

import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.dto.fcm.FcmTokenRequest
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
}
