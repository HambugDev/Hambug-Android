package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LikeBoardResponse(
    @SerialName("boardId")
    val boardId: Int = 0,
    @SerialName("likeCount")
    val likeCount: Int = 0,
    @SerialName("liked")
    val liked: Boolean = false
)
