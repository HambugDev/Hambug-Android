package desktop.hambug.domain.model

data class HomeBoard(
    val id: Int,
    val title: String,
    val content: String,
    val category: String,
    val imageUrl: String?,
    val createdAt: String,
    val likeCount: Int,
    val commentCount: Int
)
