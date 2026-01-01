package desktop.hambug.domain.repository

import android.net.Uri
import desktop.hambug.domain.model.MyBoard
import desktop.hambug.domain.model.MyComment
import desktop.hambug.domain.model.UserInfo

interface MyRepository {
    suspend fun getUserInfo(): UserInfo
    suspend fun updateUserNickname(userId: Int, nickname: String): UserInfo
    suspend fun updateUserProfileImage(userId: Int, imageUri: Uri?): UserInfo
    suspend fun getMyBoards(): List<MyBoard>
    suspend fun getMyComments(): List<MyComment>
}
