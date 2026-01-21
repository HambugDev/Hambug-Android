package desktop.hambug.data.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("token")
    val token: LoginToken? = null,
    @SerialName("user")
    val user: LoginUser? = null
)

@Serializable
data class LoginToken(
    @SerialName("accessToken")
    val accessToken: String? = null,
    @SerialName("refreshToken")
    val refreshToken: String? = null
)

@Serializable
data class LoginUser(
    @SerialName("isRegister")
    val isRegister: Boolean? = false,
    @SerialName("kakao")
    val kakao: Boolean = false,
    @SerialName("loginType")
    val loginType: String? = "",
    @SerialName("nickname")
    val nickname: String? = "",
    @SerialName("profileImageUrl")
    val profileImageUrl: String? = "",
    @SerialName("role")
    val role: String = "",
    @SerialName("userId")
    val userId: Int = 0
)
