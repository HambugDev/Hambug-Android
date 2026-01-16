package desktop.hambug.domain.repository

import desktop.hambug.domain.model.Noti

interface FcmRepository {
    suspend fun updateFcmToken(token: String)
    suspend fun getNotis(): List<Noti>
}
