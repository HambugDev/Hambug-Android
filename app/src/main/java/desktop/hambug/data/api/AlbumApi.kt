package desktop.hambug.data.api

import desktop.hambug.data.dto.AlbumDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {
    @GET("albums")
    suspend fun getAlbums(
        @Query("userId") userId: Int
    ): List<AlbumDto>
}
