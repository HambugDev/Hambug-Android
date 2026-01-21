package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyBoardsResponse(
    @SerialName("nextPage")
    val nextPage: Boolean = false,
    @SerialName("nextCursorId")
    val nextCursorId: Int = -1,
    @SerialName("content")
    val content: List<MyBoardItem> = emptyList()
)

@Serializable
data class MyBoardItem(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    val title: String = "",
    @SerialName("content")
    val content: String = "",
    @SerialName("authorNickname")
    val authorNickname: String = "햄린이_0123456789",
    @SerialName("viewCount")
    val viewCount: Int = 0,
    @SerialName("commentCount")
    val commentCount: Int = 0,
    @SerialName("likeCount")
    val likeCount: Int = 0,
    @SerialName("category")
    val category: String = "",
    @SerialName("imageUrls")
    val imageUrls: List<String> = emptyList(),
    @SerialName("createAt")
    val createAt: String = ""
)
