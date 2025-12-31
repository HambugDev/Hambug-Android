package desktop.hambug.presentation.my

import desktop.hambug.domain.model.MyComment

sealed class MyCommentUiState {
    data object Loading: MyCommentUiState()
    data class Success(val comments: List<MyComment>): MyCommentUiState()
    data class Error(val message: String): MyCommentUiState()
}
