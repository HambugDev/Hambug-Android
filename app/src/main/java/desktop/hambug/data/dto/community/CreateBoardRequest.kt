package desktop.hambug.data.dto.community

import kotlinx.serialization.Serializable

@Serializable
data class CreateBoardRequest(
    val title: String,
    val content: String,
    val category: String
)
