package desktop.hambug.data.dto.my

import kotlinx.serialization.Serializable

@Serializable
data class NicknameUpdateRequest(
    val nickname: String
)
