package desktop.hambug.domain.repository

import android.content.Context
import desktop.hambug.data.dto.auth.LoginResponse

interface AuthRepository {
    suspend fun loginWithKakao(context: Context): LoginResponse
    suspend fun loginWithApple(identityToken: String): LoginResponse
    suspend fun logout()
    suspend fun unlink(provider: String)
}
