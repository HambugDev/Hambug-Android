package desktop.hambug.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("success")
    val success: Boolean = false,
    @SerialName("data")
    val data: T? = null,
    @SerialName("message")
    val message: String = ""
)
