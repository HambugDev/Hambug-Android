package desktop.hambug.presentation.community

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Comment
import desktop.hambug.domain.usecase.CreateCommentUseCase
import desktop.hambug.domain.usecase.DeleteBoardUseCase
import desktop.hambug.domain.usecase.DeleteCommentUseCase
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
    private val deleteBoardUseCase: DeleteBoardUseCase,
    private val likeBoardUseCase: LikeBoardUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val createCommentUseCase: CreateCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase
) : ViewModel() {

    private val boardId: Int = checkNotNull(savedStateHandle["boardId"])
    val isNewBoard: Boolean = savedStateHandle["isNewBoard"] ?: false

    private val _uiState = MutableStateFlow<BoardDetailUiState>(BoardDetailUiState.Loading)
    val uiState: StateFlow<BoardDetailUiState> = _uiState.asStateFlow()

    private val _commentsState = MutableStateFlow<CommentsUiState>(CommentsUiState.Loading)
    val commentsState: StateFlow<CommentsUiState> = _commentsState.asStateFlow()

    private val _selectedComment = MutableStateFlow<Comment?>(null)
    val selectedComment = _selectedComment.asStateFlow()

    // 좋아요 처리중 상태
    private val _isLikeProcessing = MutableStateFlow(false)
    val isLikeProcessing: StateFlow<Boolean> = _isLikeProcessing.asStateFlow()

    // 댓글 입력 상태
    private val _commentText = MutableStateFlow("")
    val commentText: StateFlow<String> = _commentText.asStateFlow()

    // 댓글 삭제 스낵바
    private val _showCommentDeleteSnackbar = MutableStateFlow(false)
    val showCommentDeleteSnackbar: StateFlow<Boolean> = _showCommentDeleteSnackbar.asStateFlow()

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
     * 게시물 삭제
     */
    fun deleteBoard(onSuccess: () -> Unit) {
        viewModelScope.launch {
            deleteBoardUseCase(boardId)
                .onSuccess {
                    onSuccess()
                }
                .onFailure { exception ->
                    Log.e("my", "게시물 삭제 실패: ${exception.message}", exception)
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

    fun onCommentTextChange(text: String) {
        _commentText.value = text
    }

    /**
     * 댓글 생성
     */
    fun createComment() {
        val comment = _commentText.value.trim()
        if (comment.isEmpty()) return

        viewModelScope.launch {
            createCommentUseCase(boardId, comment)
                .onSuccess {
                    _commentText.value = ""
                    loadComments()
                }
                .onFailure { exception ->
                    Log.e("community", "댓글 생성 실패: ${exception.message}", exception)
                }
        }
    }

    /**
     * 선택된 댓글 저장
     */
    fun onCommentClicked(comment: Comment) {
        _selectedComment.value = comment
    }

    /**
     * 댓글 삭제
     */
    fun deleteComment(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val comment = _selectedComment.value ?: return@launch

            deleteCommentUseCase(boardId = boardId, commentId = comment.id)
                .onSuccess {
                    clearSelectedComment()
                    loadComments()
                    onSuccess()
                    _showCommentDeleteSnackbar.value = true
                }
                .onFailure { exception ->
                    Log.e("community", "댓글 삭제 실패: ${exception.message}", exception)
                }
        }
    }

    /**
     * 스낵바 표시 완료 처리
     */
    fun onCommentDeleteSnackbarShown() {
        _showCommentDeleteSnackbar.value = false
    }

    /**
     * 선택된 댓글 초기화
     */
    fun clearSelectedComment() {
        _selectedComment.value = null
    }
}
