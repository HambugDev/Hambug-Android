package desktop.hambug.domain.repository

interface FcmRepository {
    suspend fun updateFcmToken(token: String)
}
