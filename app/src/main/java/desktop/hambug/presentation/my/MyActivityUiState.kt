package desktop.hambug.presentation.my

import desktop.hambug.domain.model.MyBoard

sealed class MyActivityUiState {
    data object Loading : MyActivityUiState()
    data class Success(val boards: List<MyBoard>) : MyActivityUiState()
    data object Error : MyActivityUiState()
}
