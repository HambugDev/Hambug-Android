package desktop.hambug.data.dto.fcm

import kotlinx.serialization.Serializable

@Serializable
data class FcmTokenRequest(
    val token: String,
    val platform: String
)
