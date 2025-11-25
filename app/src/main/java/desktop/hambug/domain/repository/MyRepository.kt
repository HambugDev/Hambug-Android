package desktop.hambug.domain.repository

import desktop.hambug.domain.model.UserInfo

interface MyRepository {
    suspend fun getUserInfo(): UserInfo
    suspend fun updateUserNickname(userId: Int, nickname: String): UserInfo
}
