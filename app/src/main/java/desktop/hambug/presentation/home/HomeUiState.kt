package desktop.hambug.presentation.home

import desktop.hambug.domain.model.HomeBurger

sealed class HomeUiState {
    data object Loading: HomeUiState()
    data class Success(val burgers: List<HomeBurger>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
