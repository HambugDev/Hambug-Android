package desktop.hambug.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import desktop.hambug.data.repository.AlbumRepositoryImpl
import desktop.hambug.domain.repository.AlbumRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAlbumRepository(albumRepositoryImpl: AlbumRepositoryImpl): AlbumRepository {
        return albumRepositoryImpl
    }
}
