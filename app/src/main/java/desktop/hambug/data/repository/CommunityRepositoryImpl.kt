package desktop.hambug.data.repository

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.dto.community.CreateBoardRequest
import desktop.hambug.data.dto.community.CreateCommentRequest
import desktop.hambug.data.dto.community.LikeBoardResponse
import desktop.hambug.data.dto.report.ReportRequest
import desktop.hambug.data.mapper.toEntity
import desktop.hambug.domain.model.BoardDetail
import desktop.hambug.domain.model.BoardPage
import desktop.hambug.domain.model.Comment
import desktop.hambug.domain.repository.CommunityRepository
import desktop.hambug.util.ImageFileUtil
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val hambugApi: HambugApi,
    @ApplicationContext private val context: Context
) : CommunityRepository {
    override suspend fun getBoards(lastId: Int?): BoardPage {
        val response = hambugApi.getBoards(lastId)

        if (!response.success) {
            throw Exception(response.message)
        }

        val boardsData = response.data ?: throw Exception("boards 데이터 없음")

        return boardsData.toEntity()
    }

    override suspend fun getCategoryBoards(category: String, lastId: Int?): BoardPage {
        val response = hambugApi.getCategoryBoards(category, lastId)

        if (!response.success) {
            throw Exception(response.message)
        }

        val boardsData = response.data ?: throw Exception("categoryBoards 데이터 없음")

        return boardsData.toEntity()
    }

    override suspend fun getBoardDetail(boardId: Int): BoardDetail {
        val response = hambugApi.getBoardDetail(boardId)

        if (!response.success) {
            throw Exception(response.message)
        }

        val boardDetailData = response.data ?: throw Exception("boardDetail 데이터 없음")

        return boardDetailData.toEntity()
    }

    override suspend fun createBoard(title: String, content: String, category: String): Int {
        val response = hambugApi.createBoard(CreateBoardRequest(title, content, category))

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data?.id ?: throw Exception("유효하지 않은 게시물 ID")
    }

    override suspend fun createBoardWithImages(
        title: String,
        content: String,
        category: String,
        imageUris: List<Uri>
    ): Int {
        // JSON 요청 본문 생성
        val requestJson = JSONObject().apply {
            put("title", title)
            put("content", content)
            put("category", category)
        }.toString()

        val requestBody = requestJson.toRequestBody("application/json".toMediaType())

        // 이미지들을 MultipartBody.Part 리스트로 변환
        val imageParts = ImageFileUtil.createMultipartBodyParts(
            context = context,
            fileUris = imageUris,
            partName = "images"
        )

        if (imageParts.isEmpty()) {
            throw Exception("업로드 가능한 이미지가 없습니다")
        }

        val response = hambugApi.createBoardWithImages(requestBody, imageParts)

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data?.id ?: throw Exception("유효하지 않은 게시물 ID")
    }

    override suspend fun deleteBoard(boardId: Int) {
        val response = hambugApi.deleteBoard(boardId)

        if (!response.success) {
            throw Exception(response.message)
        }
    }

    override suspend fun likeBoard(boardId: Int): LikeBoardResponse {
        val response = hambugApi.likeBoard(boardId)

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data ?: throw Exception("likeBoard 데이터 없음")
    }

    override suspend fun getComments(boardId: Int): List<Comment> {
        val response = hambugApi.getComments(boardId)

        if (!response.success) {
            throw Exception(response.message)
        }

        // mapNotNull을 사용하여 id가 없는 아이템 제거
        return response.data?.content?.mapNotNull { it.toEntity() } ?: emptyList()
    }

    override suspend fun createComment(boardId: Int, content: String) {
        val response = hambugApi.createComment(
            boardId = boardId,
            request = CreateCommentRequest(content)
        )

        if (!response.success) {
            throw Exception(response.message)
        }
    }

    override suspend fun deleteComment(boardId: Int, commentId: Int) {
        val response = hambugApi.deleteComment(boardId, commentId)

        if (!response.success) {
            throw Exception(response.message)
        }
    }

    override suspend fun report(targetId: Int, reportType: String, title: String, content: String) {
        val response = hambugApi.report(ReportRequest(targetId, reportType, title, content))

        if (!response.success) {
            throw Exception(response.message)
        }
    }
}
