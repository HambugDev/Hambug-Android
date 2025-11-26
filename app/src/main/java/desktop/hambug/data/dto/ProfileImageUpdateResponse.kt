package desktop.hambug.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileImageUpdateResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: UserInfoData,
    @SerialName("message")
    val message: String
)
