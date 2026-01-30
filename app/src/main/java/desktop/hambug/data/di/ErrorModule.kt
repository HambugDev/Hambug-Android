package desktop.hambug.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import desktop.hambug.util.ErrorHandler
import desktop.hambug.util.ErrorHandlerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ErrorModule {

    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandler = ErrorHandlerImpl()
}
