package desktop.hambug.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import desktop.hambug.data.repository.AlbumRepositoryImpl
import desktop.hambug.data.repository.KakaoLoginRepositoryImpl
import desktop.hambug.domain.repository.AlbumRepository
import desktop.hambug.domain.repository.KakaoLoginRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAlbumRepository(
        albumRepositoryImpl: AlbumRepositoryImpl
    ): AlbumRepository

    @Binds
    @Singleton
    abstract fun bindKakaoLoginRepository(
        kakaoLoginRepositoryImpl: KakaoLoginRepositoryImpl
    ): KakaoLoginRepository
}
