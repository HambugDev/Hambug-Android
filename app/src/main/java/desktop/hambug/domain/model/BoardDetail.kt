package desktop.hambug.domain.model

data class BoardDetail(
    val id: Int,
    val title: String,
    val content: String,
    val imageUrls: List<String>?,
    val authorNickname: String,
    val authorProfileImageUrl: String,
    val createdAt: String,
    val likeCount: Int,
    val commentCount: Int,
    val isLiked: Boolean,
    val isAuthor: Boolean
)
