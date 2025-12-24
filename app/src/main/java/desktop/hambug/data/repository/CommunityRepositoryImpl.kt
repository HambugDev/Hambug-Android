package desktop.hambug.data.repository

import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.dto.community.CreateBoardRequest
import desktop.hambug.data.mapper.toEntity
import desktop.hambug.domain.model.Board
import desktop.hambug.domain.model.BoardDetail
import desktop.hambug.domain.model.BoardPage
import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val hambugApi: HambugApi
) : CommunityRepository {
    override suspend fun getBoards(lastId: Int?): BoardPage {
        val response = hambugApi.getBoards(lastId)

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.toEntity()
    }

    override suspend fun getCategoryBoards(category: String): List<Board> {
        val response = hambugApi.getCategoryBoards(category)

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.map { it.toEntity() }
    }

    override suspend fun getBoardDetail(boardId: Int): BoardDetail {
        val response = hambugApi.getBoardDetail(boardId)

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.toEntity()
    }

    override suspend fun createBoard(title: String, content: String, category: String): Int {
        val response = hambugApi.createBoard(CreateBoardRequest(title, content, category))

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.id
    }
}
