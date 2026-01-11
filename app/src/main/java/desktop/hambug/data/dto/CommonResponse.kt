package desktop.hambug.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: Boolean,
    @SerialName("message")
    val message: String
)
