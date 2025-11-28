package desktop.hambug.domain.model

data class Board(
    val id: Int,
    val title: String,
    val content: String,
    val imageUrl: String?,
    val authorNickname: String,
    val createdAt: String,
    val likeCount: Int
)
