package desktop.hambug.data.api

import desktop.hambug.data.dto.home.HomeBurgerResponse
import desktop.hambug.data.dto.LoginRequest
import desktop.hambug.data.dto.LoginResponse
import desktop.hambug.data.dto.NicknameUpdateRequest
import desktop.hambug.data.dto.NicknameUpdateResponse
import desktop.hambug.data.dto.ProfileImageUpdateResponse
import desktop.hambug.data.dto.UserInfoResponse
import desktop.hambug.data.dto.community.BoardDetailResponse
import desktop.hambug.data.dto.community.BoardsResponse
import desktop.hambug.data.dto.community.CreateBoardRequest
import desktop.hambug.data.dto.community.CreateBoardResponse
import desktop.hambug.data.dto.home.HomeBoardResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface HambugApi {
    @POST("auth/login/{provider}")
    suspend fun login(
        @Path("provider") provider: String,
        @Body request: LoginRequest
    ): LoginResponse

    // 오늘의 추천 햄버거 조회
    @GET("burgers/recommended")
    suspend fun getHomeBurgers(): HomeBurgerResponse

    // 인기 게시물 조회
    @GET("boards/trending")
    suspend fun getHomeBoards(): HomeBoardResponse

    // 게시물 전체 조회
    @GET("boards")
    suspend fun getBoards(
        @Query("lastId") lastId: Int? = null
    ): BoardsResponse

    // 카테고리별 게시물 조회
    @GET("boards/category")
    suspend fun getCategoryBoards(
        @Query("category") category: String,
        @Query("lastId") lastId: Int? = null
    ): BoardsResponse

    // 게시물 상세 조회
    @GET("boards/{id}")
    suspend fun getBoardDetail(
        @Path("id") id: Int
    ): BoardDetailResponse

    // 게시물 생성
    @POST("boards")
    suspend fun createBoard(
        @Body request: CreateBoardRequest
    ): CreateBoardResponse

    // 게시물 생성 (이미지 포함)
    @Multipart
    @POST("boards/with-images")
    suspend fun createBoardWithImages(
        @Part("request") request: RequestBody,
        @Part images: List<MultipartBody.Part>
    ): CreateBoardResponse

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
