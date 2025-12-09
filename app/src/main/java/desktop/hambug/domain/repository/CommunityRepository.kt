package desktop.hambug.domain.repository

import desktop.hambug.domain.model.Board
import desktop.hambug.domain.model.BoardDetail

interface CommunityRepository {
    suspend fun getBoards(): List<Board>
    suspend fun getCategoryBoards(category: String): List<Board>
    suspend fun getBoardDetail(boardId: Int): BoardDetail
}
