package desktop.hambug.domain.repository

import desktop.hambug.domain.model.Album

interface AlbumRepository {
    suspend fun getAlbums(userId: Int): List<Album>
}
