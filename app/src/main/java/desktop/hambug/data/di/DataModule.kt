package desktop.hambug.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import desktop.hambug.data.repository.BurgerRepositoryImpl
import desktop.hambug.domain.repository.BurgerRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindBurgerRepository(
        burgerRepositoryImpl: BurgerRepositoryImpl
    ) : BurgerRepository
}
