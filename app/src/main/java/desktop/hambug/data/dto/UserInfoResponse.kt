package desktop.hambug.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: UserInfoData,
    @SerialName("message")
    val message: String
)

@Serializable
data class UserInfoData(
    @SerialName("userId")
    val userId: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String,
    @SerialName("loginType")
    val loginType: String,
    @SerialName("role")
    val role: String,
    @SerialName("isRegister")
    val isRegister: Boolean?,
    @SerialName("kakao")
    val kakao: Boolean
)
