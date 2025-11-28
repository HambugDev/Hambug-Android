package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryBoardsResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: List<BoardData>,
    @SerialName("message")
    val message: String
)
