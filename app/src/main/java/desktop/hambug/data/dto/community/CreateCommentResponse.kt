package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String
)
