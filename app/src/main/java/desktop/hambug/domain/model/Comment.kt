package desktop.hambug.domain.model

data class Comment(
    val id: Int,
    val content: String,
    val authorId: Int,
    val authorNickname: String,
    val authorProfileImageUrl: String,
    val createdAt: String?
)
