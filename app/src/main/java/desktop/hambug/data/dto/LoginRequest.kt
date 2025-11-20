package desktop.hambug.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val accessToken: String
)
