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
    val content: List<NotiItem>,
    @SerialName("lastId")
    val lastId: Int,
    @SerialName("hasNext")
    val hasNext: Boolean
)

@Serializable
data class NotiItem(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("type")
    val type: String,
    @SerialName("targetId")
    val targetId: Int,
    @SerialName("isRead")
    val isRead: Boolean,
    @SerialName("createdAt")
    val createdAt: String
)
