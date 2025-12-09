package desktop.hambug.presentation.community

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.GetBoardDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardDetailViewModel @Inject constructor(
    private val boardDetailUseCase: GetBoardDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val boardId: Int = checkNotNull(savedStateHandle["boardId"])

    // 게시물 상세
    private val _uiState = MutableStateFlow<BoardDetailUiState>(BoardDetailUiState.Loading)
    val uiState: StateFlow<BoardDetailUiState> =  _uiState.asStateFlow()

    init {
        loadBoardDate()
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
}
