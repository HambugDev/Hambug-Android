package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteCommentResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: Boolean,
    @SerialName("message")
    val message: String
)
