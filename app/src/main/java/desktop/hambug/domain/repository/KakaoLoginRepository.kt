package desktop.hambug.domain.repository

import android.content.Context
import desktop.hambug.data.dto.LoginData

interface KakaoLoginRepository {
    suspend fun login(context: Context): LoginData
}
