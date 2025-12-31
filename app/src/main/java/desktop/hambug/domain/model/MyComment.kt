package desktop.hambug.domain.model

data class MyComment(
    val boardId: Int,
    val boardTitle: String,
    val commentId: Int,
    val commentContent: String,
    val createdAt: String
)
