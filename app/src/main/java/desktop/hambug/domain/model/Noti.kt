package desktop.hambug.domain.model

data class Noti(
    val notiId: Int,
    val title: String,
    val content: String,
    val type: String,
    val targetId: Int,
    val thumbnailUrl: String,
    val createdAt: String
)
