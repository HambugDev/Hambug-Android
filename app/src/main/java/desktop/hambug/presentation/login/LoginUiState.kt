package desktop.hambug.presentation.login

sealed class LoginUiState {
    data class Default(val isProcessing: Boolean = false) : LoginUiState()
    data object Error : LoginUiState()
}
