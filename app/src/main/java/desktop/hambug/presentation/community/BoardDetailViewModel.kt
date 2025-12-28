package desktop.hambug.presentation.community

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.GetBoardDetailUseCase
import desktop.hambug.domain.usecase.GetCommentsUseCase
import desktop.hambug.domain.usecase.LikeBoardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val boardDetailUseCase: GetBoardDetailUseCase,
    private val likeBoardUseCase: LikeBoardUseCase,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {

    private val boardId: Int = checkNotNull(savedStateHandle["boardId"])

    private val _uiState = MutableStateFlow<BoardDetailUiState>(BoardDetailUiState.Loading)
    val uiState: StateFlow<BoardDetailUiState> =  _uiState.asStateFlow()

    private val _commentsState = MutableStateFlow<CommentsUiState>(CommentsUiState.Loading)
    val commentsState: StateFlow<CommentsUiState> = _commentsState.asStateFlow()

    // 좋아요 처리중 상태
    private val _isLikeProcessing = MutableStateFlow(false)
    val isLikeProcessing: StateFlow<Boolean> = _isLikeProcessing.asStateFlow()

    init {
        loadBoardDate()
        loadComments()
    }

    private fun loadBoardDate() {
        viewModelScope.launch {
            boardDetailUseCase(boardId)
                .onSuccess { board ->
                    _uiState.value = BoardDetailUiState.Success(board)
                }
                .onFailure { exception ->
                    val exceptionMessage = exception.message ?: "게시물 상세 데이터 로딩 실패"
                    _uiState.value = BoardDetailUiState.Error(exceptionMessage)
                }
        }
    }

    private fun loadComments() {
        viewModelScope.launch {
            getCommentsUseCase(boardId)
                .onSuccess { comments ->
                    _commentsState.value = CommentsUiState.Success(comments)
                }
                .onFailure { exception ->
                    val exceptionMessage = exception.message ?: "댓글 로딩 실패"
                    _commentsState.value = CommentsUiState.Error(exceptionMessage)
                }
        }
    }

    /**
     * 좋아요 토글 (좋아요, 좋아요 취소)
     */
    fun likeBoard() {
        // 현재 success 상태가 아니면 반환
        val currentState = _uiState.value
        if (currentState !is BoardDetailUiState.Success) return

        // 이미 처리중이면 반환 (중복 클릭 방지)
        if (_isLikeProcessing.value) return

        viewModelScope.launch {
            _isLikeProcessing.value = true

            // 현재 데이터
            val previousBoard = currentState.board

            // UI 즉시 반영
            val newBoard = previousBoard.copy(
                isLiked = !previousBoard.isLiked,
                likeCount = if (previousBoard.isLiked) {
                    previousBoard.likeCount - 1
                } else {
                    previousBoard.likeCount + 1
                }
            )
            _uiState.value = BoardDetailUiState.Success(newBoard)

            // API 호출
            likeBoardUseCase(boardId)
                .onSuccess { response ->
                    _uiState.value = BoardDetailUiState.Success(
                        previousBoard.copy(
                            isLiked = response.liked,
                            likeCount = response.likeCount
                        )
                    )
                    Log.d("community", "likeBoard 성공")
                }
                .onFailure { exception ->
                    // 실패 시 이전 상태로
                    _uiState.value = BoardDetailUiState.Success(previousBoard)
                    Log.e("community", "likeBoard 실패: ${exception.message}", exception)
                }

            _isLikeProcessing.value = false
        }
    }
}
