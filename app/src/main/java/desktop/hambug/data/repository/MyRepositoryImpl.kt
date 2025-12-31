package desktop.hambug.data.repository

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.dto.NicknameUpdateRequest
import desktop.hambug.data.mapper.toEntity
import desktop.hambug.domain.model.MyBoard
import desktop.hambug.domain.model.MyComment
import desktop.hambug.domain.model.UserInfo
import desktop.hambug.domain.repository.MyRepository
import desktop.hambug.util.ImageFileUtil
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val hambugApi: HambugApi,
    @ApplicationContext private val context: Context
) : MyRepository {

    override suspend fun getUserInfo(): UserInfo {
        val response = hambugApi.getUserInfo()

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.toEntity()
    }

    override suspend fun updateUserNickname(userId: Int, nickname: String): UserInfo {
        val request = NicknameUpdateRequest(nickname)
        val response = hambugApi.putUserNickname(id = userId, request = request)

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.toEntity()
    }

    override suspend fun updateUserProfileImage(userId: Int, imageUri: Uri): UserInfo {
        // 이미지를 MultipartBody.Part로 변환
        val imagePart = ImageFileUtil.createMultipartBodyPart(
            context = context,
            fileUri = imageUri,
            partName = "file"
        ) ?: throw Exception("이미지를 처리할 수 없습니다")

        val response = hambugApi.putUserProfileImage(userId, imagePart)

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.toEntity()
    }

    override suspend fun getMyBoards(): List<MyBoard> {
        val response = hambugApi.getMyBoards()

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.content.map { it.toEntity() }
    }

    override suspend fun getMyComments(): List<MyComment> {
        val response = hambugApi.getMyComments()

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data.content.map { it.toEntity() }
    }
}
