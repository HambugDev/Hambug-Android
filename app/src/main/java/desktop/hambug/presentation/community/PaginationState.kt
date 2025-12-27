package desktop.hambug.presentation.community

import desktop.hambug.domain.model.Board
import desktop.hambug.domain.model.BoardPage

data class PaginationState(
    val boards: List<Board> = emptyList(),
    val nextCursorId: Int? = null,
    val hasNextPage: Boolean = false
) {
    fun updateWithNewPage(page: BoardPage): PaginationState {
        return copy(
            boards = boards + page.content,
            nextCursorId = page.nextCursorId,
            hasNextPage = page.nextPage
        )
    }
}
