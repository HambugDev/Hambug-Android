package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyCommentsResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: MyCommentsData,
    @SerialName("message")
    val message: String
)

@Serializable
data class MyCommentsData(
    @SerialName("nextPage")
    val nextPage: Boolean,
    @SerialName("nextCursorId")
    val nextCursorId: Int?,
    @SerialName("content")
    val content: List<MyCommentItem>
)

@Serializable
data class MyCommentItem(
    @SerialName("boardId")
    val boardId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("commentId")
    val commentId: Int,
    @SerialName("content")
    val content: String,
    @SerialName("createdAt")
    val createdAt: String
)
