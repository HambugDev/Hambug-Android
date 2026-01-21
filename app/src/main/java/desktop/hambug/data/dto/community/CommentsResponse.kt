package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentsResponse(
    @SerialName("content")
    val content: List<CommentItem> = emptyList(),
    @SerialName("nextCursorId")
    val nextCursorId: Int = -1,
    @SerialName("nextPage")
    val nextPage: Boolean = false
)

@Serializable
data class CommentItem(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("content")
    val content: String = "",
    @SerialName("authorId")
    val authorId: Int = 0,
    @SerialName("authorNickname")
    val authorNickname: String = "햄린이_0123456789",
    @SerialName("authorProfileImageUrl")
    val authorProfileImageUrl: String = "",
    @SerialName("isAuthor")
    val isAuthor: Boolean = false,
    @SerialName("createdAt")
    val createdAt: String = "",
    @SerialName("updatedAt")
    val updatedAt: String = ""
)
