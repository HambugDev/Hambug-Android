package desktop.hambug.domain.model

data class BoardPage(
    val content: List<Board>,
    val nextCursorId: Int,
    val nextPage: Boolean
)
