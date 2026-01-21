package desktop.hambug.data.dto.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateBoardResponse(
    @SerialName("id")
    val id: Int? = null
)
