package desktop.hambug.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NicknameUpdateRequest(
    val nickname: String
)
