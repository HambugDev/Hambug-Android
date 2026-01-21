package desktop.hambug.data.dto.my

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("userId")
    val userId: Int = 0,
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("profileImageUrl")
    val profileImageUrl: String = "",
    @SerialName("loginType")
    val loginType: String = "",
    @SerialName("role")
    val role: String = "",
    @SerialName("isRegister")
    val isRegister: Boolean = false,
    @SerialName("kakao")
    val kakao: Boolean = false
)
