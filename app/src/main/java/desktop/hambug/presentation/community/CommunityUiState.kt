package desktop.hambug.presentation.community

import desktop.hambug.domain.model.Board

sealed class CommunityUiState {
    data object Loading: CommunityUiState()
    data class Success(val boards: List<Board>) : CommunityUiState()
    data class Error(val message: String) : CommunityUiState()
}
