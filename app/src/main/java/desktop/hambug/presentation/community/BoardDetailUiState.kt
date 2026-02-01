package desktop.hambug.presentation.community

import desktop.hambug.domain.model.BoardDetail

sealed class BoardDetailUiState {
    data object Loading : BoardDetailUiState()
    data class Success(val board: BoardDetail) : BoardDetailUiState()
    data object Error : BoardDetailUiState()
}
