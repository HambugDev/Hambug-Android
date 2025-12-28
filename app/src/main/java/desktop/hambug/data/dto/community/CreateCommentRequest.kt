package desktop.hambug.data.dto.community

import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentRequest(
    val content: String
)
