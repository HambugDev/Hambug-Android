package desktop.hambug.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import desktop.hambug.data.repository.CommunityRepositoryImpl
import desktop.hambug.data.repository.HomeRepositoryImpl
import desktop.hambug.data.repository.KakaoLoginRepositoryImpl
import desktop.hambug.data.repository.MyRepositoryImpl
import desktop.hambug.domain.repository.CommunityRepository
import desktop.hambug.domain.repository.HomeRepository
import desktop.hambug.domain.repository.KakaoLoginRepository
import desktop.hambug.domain.repository.MyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindKakaoLoginRepository(
        kakaoLoginRepositoryImpl: KakaoLoginRepositoryImpl
    ): KakaoLoginRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: MyRepositoryImpl
    ): MyRepository

    @Binds
    @Singleton
    abstract fun bindCommunityRepository(
        communityRepositoryImpl: CommunityRepositoryImpl
    ): CommunityRepository
}
