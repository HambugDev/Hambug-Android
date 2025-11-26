package desktop.hambug.data.api

import desktop.hambug.data.dto.HomeBurgerResponse
import desktop.hambug.data.dto.LoginRequest
import desktop.hambug.data.dto.LoginResponse
import desktop.hambug.data.dto.NicknameUpdateRequest
import desktop.hambug.data.dto.NicknameUpdateResponse
import desktop.hambug.data.dto.ProfileImageUpdateResponse
import desktop.hambug.data.dto.UserInfoResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface HambugApi {
    @POST("auth/login/{provider}")
    suspend fun login(
        @Path("provider") provider: String,
        @Body request: LoginRequest
    ): LoginResponse

    // 오늘의 추천 햄버거 조회
    @GET("burgers/recommended")
    suspend fun getHomeBurgers(): HomeBurgerResponse

    // JWT 토큰으로 내 정보 조회
    @GET("auth/me")
    suspend fun getUserInfo(): UserInfoResponse
    // 닉네임 변경
    @PUT("users/{id}/nickname")
    suspend fun putUserNickname(
        @Path("id") id: Int,
        @Body request: NicknameUpdateRequest
    ): NicknameUpdateResponse
    // 프로필 이미지 변경
    @Multipart
    @PUT("users/{id}/profile")
    suspend fun putUserProfileImage(
        @Path("id") id: Int,
        @Part file: MultipartBody.Part
    ): ProfileImageUpdateResponse
}
