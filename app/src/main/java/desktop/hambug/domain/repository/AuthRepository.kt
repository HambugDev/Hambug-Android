package desktop.hambug.domain.repository

import android.content.Context
import desktop.hambug.data.dto.LoginData

interface AuthRepository {
    suspend fun login(context: Context): LoginData
    suspend fun logout()
    suspend fun unlink(provider: String)
}
