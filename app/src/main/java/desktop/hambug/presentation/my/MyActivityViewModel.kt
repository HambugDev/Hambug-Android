package desktop.hambug.presentation.my

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.my.GetMyBoardsUseCase
import desktop.hambug.domain.usecase.my.GetMyCommentsUseCase
import desktop.hambug.presentation.base.BaseViewModel
import desktop.hambug.util.ErrorHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyActivityViewModel @Inject constructor(
    private val getMyBoardsUseCase: GetMyBoardsUseCase,
    private val getMyCommentsUseCase: GetMyCommentsUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    private val _uiState = MutableStateFlow<MyActivityUiState>(MyActivityUiState.Loading)
    val uiState: StateFlow<MyActivityUiState> = _uiState.asStateFlow()

    private val _commentsState = MutableStateFlow<MyCommentUiState>(MyCommentUiState.Loading)
    val commentsSate: StateFlow<MyCommentUiState> = _commentsState.asStateFlow()

    init {
        loadMyBoards()
        loadMyComments()
    }

    private fun loadMyBoards() {
        viewModelScope.launch {
            getMyBoardsUseCase()
                .onSuccess { boards ->
                    _uiState.value = MyActivityUiState.Success(boards)
                }
                .onFailure { exception ->
                    handleError(exception)
                    _uiState.value = MyActivityUiState.Error
                }
        }
    }

    private fun loadMyComments() {
        viewModelScope.launch {
            getMyCommentsUseCase()
                .onSuccess { comments ->
                    _commentsState.value = MyCommentUiState.Success(comments)
                }
                .onFailure { exception ->
                    handleError(exception)
                    _commentsState.value = MyCommentUiState.Error
                }
        }
    }
}
