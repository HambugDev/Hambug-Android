package desktop.hambug.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import desktop.hambug.data.di.UtilModule
import desktop.hambug.util.FakeTestEnvironment
import desktop.hambug.util.TestEnvironment
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UtilModule::class]
)
object TestUtilModule {

    @Provides
    @Singleton
    fun provideTestEnvironment(): TestEnvironment {
        return FakeTestEnvironment()
    }
}
