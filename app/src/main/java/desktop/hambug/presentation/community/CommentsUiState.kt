package desktop.hambug.presentation.community

import desktop.hambug.domain.model.Comment

sealed class CommentsUiState {
    data object Loading: CommentsUiState()
    data class Success(val comments: List<Comment>) : CommentsUiState()
    data class Error(val message: String) : CommentsUiState()
}
