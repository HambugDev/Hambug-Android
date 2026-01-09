package desktop.hambug.domain.model

data class MyBoard(
    val id: Int,
    val title: String,
    val authorNickname: String,
    val likeCount: Int,
    val commentCount: Int,
    val imageUrl: String?,
    val createdAt: String
)
