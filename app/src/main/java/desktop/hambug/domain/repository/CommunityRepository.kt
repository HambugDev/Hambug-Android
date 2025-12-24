package desktop.hambug.domain.repository

import desktop.hambug.domain.model.Board
import desktop.hambug.domain.model.BoardDetail
import desktop.hambug.domain.model.BoardPage

interface CommunityRepository {
    suspend fun getBoards(lastId: Int?): BoardPage
    suspend fun getCategoryBoards(category: String): List<Board>
    suspend fun getBoardDetail(boardId: Int): BoardDetail
    suspend fun createBoard(title: String, content: String, category: String): Int
}
