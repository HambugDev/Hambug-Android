package desktop.hambug.presentation.noti

import desktop.hambug.domain.model.Noti

sealed class NotiUiState {
    data object Loading : NotiUiState()
    data class Success(val notis: List<Noti>) : NotiUiState()
    data object Error : NotiUiState()
}
