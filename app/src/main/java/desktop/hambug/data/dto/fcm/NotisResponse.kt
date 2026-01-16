package desktop.hambug.data.dto.fcm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotisResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: NotisData,
    @SerialName("message")
    val message: String
)

@Serializable
data class NotisData(
    @SerialName("content")
    val content: List<NotiItem> = emptyList(),
    @SerialName("lastId")
    val lastId: Int? = null,
    @SerialName("hasNext")
    val hasNext: Boolean = false
)

@Serializable
data class NotiItem(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("content")
    val content: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("targetId")
    val targetId: Int? = null,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String? = null,
    @SerialName("isRead")
    val isRead: Boolean? = null,
    @SerialName("createdAt")
    val createdAt: String? = null
)
