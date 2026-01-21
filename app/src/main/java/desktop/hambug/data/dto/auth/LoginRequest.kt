package desktop.hambug.data.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val accessToken: String
)
