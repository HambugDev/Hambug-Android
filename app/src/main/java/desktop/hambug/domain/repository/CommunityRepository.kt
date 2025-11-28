package desktop.hambug.domain.repository

import desktop.hambug.domain.model.Board

interface CommunityRepository {
    suspend fun getBoards(): List<Board>
    suspend fun getCategoryBoards(category: String): List<Board>
}
