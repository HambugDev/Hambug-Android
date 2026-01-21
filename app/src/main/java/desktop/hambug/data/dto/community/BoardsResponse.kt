package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardsResponse(
    @SerialName("content")
    val content: List<BoardItem> = emptyList(),
    @SerialName("nextCursorId")
    val nextCursorId: Int = -1,
    @SerialName("nextPage")
    val nextPage: Boolean = false
)

@Serializable
data class BoardItem(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    val title: String = "",
    @SerialName("content")
    val content: String = "",
    @SerialName("category")
    val category: String = "",
    @SerialName("imageUrls")
    val imageUrls: List<String> = emptyList(),
    @SerialName("authorNickname")
    val authorNickname: String = "햄린이_0123456789",
    @SerialName("authorId")
    val authorId: Int = 0,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("viewCount")
    val viewCount: Int,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("commentCount")
    val commentCount: Int,
    @SerialName("isLiked")
    val isLiked: Boolean
)
