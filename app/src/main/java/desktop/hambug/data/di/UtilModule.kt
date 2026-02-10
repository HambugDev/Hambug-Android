package desktop.hambug.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import desktop.hambug.util.ProductionTestEnvironment
import desktop.hambug.util.TestEnvironment
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    @Singleton
    fun provideTestEnvironment(): TestEnvironment {
        return ProductionTestEnvironment()
    }
}
