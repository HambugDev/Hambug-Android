package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyBoardsResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: MyBoardsData,
    @SerialName("message")
    val message: String
)

@Serializable
data class MyBoardsData(
    @SerialName("nextPage")
    val nextPage: Boolean,
    @SerialName("nextCursorId")
    val nextCursorId: Int?,
    @SerialName("content")
    val content: List<MyBoardItem>
)

@Serializable
data class MyBoardItem(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("viewCount")
    val viewCount: Int,
    @SerialName("commentCount")
    val commentCount: Int,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("category")
    val category: String,
    @SerialName("imageUrls")
    val imageUrls: List<String>,
    @SerialName("createAt")
    val createAt: String
)
