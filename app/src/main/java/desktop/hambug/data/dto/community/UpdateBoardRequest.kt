package desktop.hambug.data.dto.community

import kotlinx.serialization.Serializable

@Serializable
data class UpdateBoardRequest(
    val title: String,
    val content: String,
    val category: String
)
