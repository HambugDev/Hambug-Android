package desktop.hambug.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: LoginData,
    @SerialName("message")
    val message: String
)

@Serializable
data class LoginData(
    @SerialName("token")
    val token: LoginToken,
    @SerialName("user")
    val user: LoginUser
)

@Serializable
data class LoginToken(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String
)

@Serializable
data class LoginUser(
    @SerialName("isRegister")
    val isRegister: Boolean,
    @SerialName("kakao")
    val kakao: Boolean,
    @SerialName("loginType")
    val loginType: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("profileImageUrl")
    val profileImageUrl: String,
    @SerialName("role")
    val role: String,
    @SerialName("userId")
    val userId: Int
)
