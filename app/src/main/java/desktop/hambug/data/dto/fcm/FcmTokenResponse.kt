package desktop.hambug.data.dto.fcm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FcmTokenResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: Boolean,
    @SerialName("message")
    val message: String
)
