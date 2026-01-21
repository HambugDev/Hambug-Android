package desktop.hambug.domain.repository

import android.content.Context
import desktop.hambug.data.dto.auth.LoginResponse

interface AuthRepository {
    suspend fun login(context: Context): LoginResponse
    suspend fun logout()
    suspend fun unlink(provider: String)
}
