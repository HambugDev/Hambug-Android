package desktop.hambug.domain.usecase

import desktop.hambug.domain.model.Album
import desktop.hambug.domain.repository.AlbumRepository
import javax.inject.Inject

class GetAlbumListUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(userId: Int): List<Album> {
        return albumRepository.getAlbums(userId)
    }
}
