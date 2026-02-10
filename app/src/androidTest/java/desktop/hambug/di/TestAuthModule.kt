package desktop.hambug.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import desktop.hambug.data.api.HambugApi
import desktop.hambug.data.repository.CommunityRepositoryImpl
import desktop.hambug.data.repository.FcmRepositoryImpl
import desktop.hambug.data.repository.HomeRepositoryImpl
import desktop.hambug.data.repository.MyRepositoryImpl
import desktop.hambug.data.repository.fake.FakeAuthRepository
import desktop.hambug.domain.repository.AuthRepository
import desktop.hambug.domain.repository.CommunityRepository
import desktop.hambug.domain.repository.FcmRepository
import desktop.hambug.domain.repository.HomeRepository
import desktop.hambug.domain.repository.MyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return FakeAuthRepository()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        api: HambugApi
    ): HomeRepository {
        return HomeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMyRepository(
        api: HambugApi,
        @ApplicationContext context: Context
    ): MyRepository {
        return MyRepositoryImpl(api, context)
    }

    @Provides
    @Singleton
    fun provideCommunityRepository(
        api: HambugApi,
        @ApplicationContext context: Context
    ): CommunityRepository {
        return CommunityRepositoryImpl(api, context)
    }

    @Provides
    @Singleton
    fun provideFcmRepository(
        api: HambugApi
    ): FcmRepository {
        return FcmRepositoryImpl(api)
    }
}
