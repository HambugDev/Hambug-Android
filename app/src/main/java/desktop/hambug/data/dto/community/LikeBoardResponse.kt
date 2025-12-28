package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LikeBoardResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: LikeBoardData,
    @SerialName("message")
    val message: String,
)

@Serializable
data class LikeBoardData(
    @SerialName("boardId")
    val boardId: Int,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("liked")
    val liked: Boolean
)
