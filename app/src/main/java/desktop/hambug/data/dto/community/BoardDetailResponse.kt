package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardDetailResponse(
    @SerialName("id")
    val id: Int = 0,
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
    @SerialName("authorProfileImageUrl")
    val authorProfileImageUrl: String = "",
    @SerialName("authorId")
    val authorId: Int = 0,
    @SerialName("createdAt")
    val createdAt: String = "",
    @SerialName("updatedAt")
    val updatedAt: String = "",
    @SerialName("viewCount")
    val viewCount: Int = 0,
    @SerialName("likeCount")
    val likeCount: Int = 0,
    @SerialName("commentCount")
    val commentCount: Int = 0,
    @SerialName("isLiked")
    val isLiked: Boolean = false,
    @SerialName("isAuthor")
    val isAuthor: Boolean = false
)
