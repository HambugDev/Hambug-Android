package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardDetailResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: BoardDetailData,
    @SerialName("message")
    val message: String
)

@Serializable
data class BoardDetailData(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("category")
    val category: String,
    @SerialName("imageUrls")
    val imageUrls: List<String>,
    @SerialName("authorNickname")
    val authorNickname: String,
    @SerialName("authorProfileImageUrl")
    val authorProfileImageUrl: String,
    @SerialName("authorId")
    val authorId: Int,
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
