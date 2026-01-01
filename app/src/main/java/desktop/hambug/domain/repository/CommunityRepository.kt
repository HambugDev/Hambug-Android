package desktop.hambug.domain.repository

import android.net.Uri
import desktop.hambug.data.dto.community.LikeBoardData
import desktop.hambug.domain.model.BoardDetail
import desktop.hambug.domain.model.BoardPage
import desktop.hambug.domain.model.Comment

interface CommunityRepository {
    suspend fun getBoards(lastId: Int?): BoardPage
    suspend fun getCategoryBoards(category: String, lastId: Int?): BoardPage
    suspend fun getBoardDetail(boardId: Int): BoardDetail
    suspend fun createBoard(title: String, content: String, category: String): Int
    suspend fun createBoardWithImages(title: String, content: String, category: String, imageUris: List<Uri>): Int
    suspend fun deleteBoard(boardId: Int)
    suspend fun likeBoard(boardId: Int): LikeBoardData
    suspend fun getComments(boardId: Int): List<Comment>
    suspend fun createComment(boardId: Int, content: String)
    suspend fun deleteComment(boardId: Int, commentId: Int)
}
