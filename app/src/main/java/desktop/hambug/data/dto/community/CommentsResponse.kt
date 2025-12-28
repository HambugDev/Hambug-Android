package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentsResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: CommentsData,
    @SerialName("message")
    val message: String
)

@Serializable
data class CommentsData(
    @SerialName("content")
    val content: List<CommentItem> = emptyList(),
    @SerialName("netCursorId")
    val netCursorId: Int?,
    @SerialName("nextPage")
    val nextPage: Boolean
)

@Serializable
data class CommentItem(
    @SerialName("id")
    val id: Int,
    @SerialName("content")
    val content: String,
    @SerialName("authorId")
    val authorId: Int,
    @SerialName("authorNickname")
    val authorNickname: String,
    @SerialName("authorProfileImageUrl")
    val authorProfileImageUrl: String,
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("updatedAt")
    val updatedAt: String?
)
