package desktop.hambug.presentation.my

import desktop.hambug.domain.model.UserInfo

sealed class MyUiState {
    data object Loading : MyUiState()
    data class Success(val userInfo: UserInfo) : MyUiState()
    data object Error : MyUiState()
}
