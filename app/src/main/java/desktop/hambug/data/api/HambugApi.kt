package desktop.hambug.data.api

import desktop.hambug.data.dto.LoginRequest
import desktop.hambug.data.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface HambugApi {
    @POST("auth/login/{provider}")
    suspend fun login(
        @Path("provider") provider: String,
        @Body request: LoginRequest
    ): LoginResponse
}
