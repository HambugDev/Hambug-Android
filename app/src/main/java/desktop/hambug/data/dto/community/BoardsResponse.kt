package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardsResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: List<BoardData>,
    @SerialName("message")
    val message: String
)

@Serializable
data class BoardData(
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
    @SerialName("isLiked")
    val isLiked: Boolean
)
