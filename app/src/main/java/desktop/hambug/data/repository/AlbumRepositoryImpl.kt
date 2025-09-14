package desktop.hambug.data.repository

import desktop.hambug.data.api.AlbumApi
import desktop.hambug.domain.model.Album
import desktop.hambug.domain.repository.AlbumRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumApi: AlbumApi
) : AlbumRepository {
    override suspend fun getAlbums(userId: Int): List<Album> {
        // DTO를 도메인 모델로 변환
        return albumApi.getAlbums(userId).map { albumDto ->
            Album(
                id = albumDto.id,
                title = albumDto.title
            )
        }
    }
}
