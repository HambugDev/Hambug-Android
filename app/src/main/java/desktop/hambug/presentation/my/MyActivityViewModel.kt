package desktop.hambug.presentation.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.GetMyBoardsUseCase
import desktop.hambug.domain.usecase.GetMyCommentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyActivityViewModel @Inject constructor(
    private val getMyBoardsUseCase: GetMyBoardsUseCase,
    private val getMyCommentsUseCase: GetMyCommentsUseCase
) : ViewModel() {

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
                    Log.e("my", "getUserInfo 실패: ${exception.message}", exception)
                    val exceptionMessage = exception.message ?: "내 게시물 목록 조회 실패"
                    _uiState.value = MyActivityUiState.Error(exceptionMessage)
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
                    Log.e("my", "getUserInfo 실패: ${exception.message}", exception)
                    val exceptionMessage = exception.message ?: "내 댓글 목록 조회 실패"
                    _commentsState.value = MyCommentUiState.Error(exceptionMessage)
                }
        }
    }
}
