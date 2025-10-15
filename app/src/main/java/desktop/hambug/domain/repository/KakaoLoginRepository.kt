package desktop.hambug.domain.repository

import android.content.Context

interface KakaoLoginRepository {
    suspend fun login(context: Context): String
}
