package desktop.hambug.domain.model

data class Noti(
    val notiId: Int,
    val content: String,
    val type: String,
    val targetId: Int,
    val createdAt: String
)
