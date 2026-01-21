package desktop.hambug.data.dto.fcm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotisResponse(
    @SerialName("content")
    val content: List<NotiItem> = emptyList(),
    @SerialName("lastId")
    val nextCursorId: Int = -1,
    @SerialName("hasNext")
    val nextPage: Boolean = false
)

@Serializable
data class NotiItem(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("title")
    val title: String = "",
    @SerialName("content")
    val content: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("targetId")
    val targetId: Int? = null,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String = "",
    @SerialName("isRead")
    val isRead: Boolean = false,
    @SerialName("createdAt")
    val createdAt: String = ""
)
