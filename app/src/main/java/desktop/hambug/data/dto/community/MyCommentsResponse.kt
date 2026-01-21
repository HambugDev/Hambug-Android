package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyCommentsResponse(
    @SerialName("nextPage")
    val nextPage: Boolean = false,
    @SerialName("nextCursorId")
    val nextCursorId: Int = -1,
    @SerialName("content")
    val content: List<MyCommentItem> = emptyList()
)

@Serializable
data class MyCommentItem(
    @SerialName("boardId")
    val boardId: Int? = null,
    @SerialName("title")
    val title: String = "",
    @SerialName("commentId")
    val commentId: Int = 0,
    @SerialName("content")
    val content: String = "",
    @SerialName("createdAt")
    val createdAt: String = ""
)
