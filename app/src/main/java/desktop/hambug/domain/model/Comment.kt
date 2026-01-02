package desktop.hambug.domain.model

data class Comment(
    val id: Int,
    val content: String,
    val authorNickname: String,
    val authorProfileImageUrl: String,
    val isAuthor: Boolean,
    val createdAt: String
)
