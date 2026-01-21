package desktop.hambug.data.repository

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.dto.my.NicknameUpdateRequest
import desktop.hambug.data.mapper.toEntity
import desktop.hambug.domain.model.MyBoard
import desktop.hambug.domain.model.MyComment
import desktop.hambug.domain.model.UserInfo
import desktop.hambug.domain.repository.MyRepository
import desktop.hambug.util.ImageFileUtil
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
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

        val userInfoData = response.data ?: throw Exception("userInfo 데이터 없음")

        return userInfoData.toEntity()
    }

    override suspend fun updateUserNickname(userId: Int, nickname: String): UserInfo {
        val request = NicknameUpdateRequest(nickname)
        val response = hambugApi.putUserNickname(id = userId, request = request)

        if (!response.success) {
            throw Exception(response.message)
        }

        val userInfoData = response.data ?: throw Exception("userInfo 데이터 없음")

        return userInfoData.toEntity()
    }

    override suspend fun updateUserProfileImage(userId: Int, imageUri: Uri?): UserInfo {
        val imagePart = if (imageUri != null) {
            // 이미지가 있는 경우
            ImageFileUtil.createMultipartBodyPart(
                context = context,
                fileUri = imageUri,
                partName = "file"
            ) ?: throw Exception("이미지를 처리할 수 없습니다")
        } else {
            // 이미지가 없는 경우 (기본 프로필 이미지로 변경)
            val emptyRequestBody = ByteArray(0).toRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("file", "", emptyRequestBody)
        }

        val response = hambugApi.putUserProfileImage(userId, imagePart)

        if (!response.success) {
            throw Exception(response.message)
        }

        val userInfoData = response.data ?: throw Exception("userInfo 데이터 없음")

        return userInfoData.toEntity()
    }

    override suspend fun getMyBoards(): List<MyBoard> {
        val response = hambugApi.getMyBoards()

        if (!response.success) {
            throw Exception(response.message)
        }

        return response.data?.content?.mapNotNull { it.toEntity() } ?: emptyList()
    }

    override suspend fun getMyComments(): List<MyComment> {
        val response = hambugApi.getMyComments()

        if (!response.success) {
            throw Exception(response.message)
        }

        // mapNotNull을 사용하여 boardId가 없는 아이템 제거
        return response.data?.content?.mapNotNull { it.toEntity() } ?: emptyList()
    }
}
