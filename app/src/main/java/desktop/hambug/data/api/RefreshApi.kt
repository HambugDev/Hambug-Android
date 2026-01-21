package desktop.hambug.data.api

import desktop.hambug.data.dto.auth.RefreshResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface RefreshApi {
    @POST("auth/refresh")
    suspend fun refreshToken(
        @Header("Authorization") refreshToken: String
    ): RefreshResponse
}
