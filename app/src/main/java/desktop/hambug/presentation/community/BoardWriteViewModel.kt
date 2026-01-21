package desktop.hambug.presentation.community

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Category
import desktop.hambug.domain.model.CategoryType
import desktop.hambug.domain.usecase.community.CreateBoardUseCase
import desktop.hambug.presentation.component.snackbar.SnackbarMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

sealed interface BoardWriteEvent {
    data class NavigateToDetail(val boardId: Int) : BoardWriteEvent
}

@HiltViewModel
class BoardWriteViewModel @Inject constructor(
    private val createBoardUseCase: CreateBoardUseCase
) : ViewModel() {

    val categoryList = listOf(
        Category(1, "자유잡담", "자유롭게 이야기를 나눠보세요", CategoryType.FREE_TALK),
        Category(2, "프랜차이즈", "프랜차이즈 햄버거 경험을 공유해주세요", CategoryType.REVIEW),
        Category(3, "수제버거", "수제버거 경험을 공유해주세요", CategoryType.REVIEW),
        Category(4, "맛집추천", "햄버거 맛집 정보를 추천해주세요", CategoryType.RECOMMENDATION)
    )

    private val _uiState = MutableStateFlow(BoardWriteUiState())
    val uiState: StateFlow<BoardWriteUiState> = _uiState.asStateFlow()

    private val _currentCategory = MutableStateFlow(categoryList[0])
    val currentCategory: StateFlow<Category> = _currentCategory.asStateFlow()

    private val _boardTitle = MutableStateFlow("")
    val boardTitle: StateFlow<String> = _boardTitle.asStateFlow()

    private val _boardContent = MutableStateFlow("")
    val boardContent: StateFlow<String> = _boardContent.asStateFlow()

    private val _eventFlow = Channel<BoardWriteEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    // 최근에 발행된 메시지 1개 저장
    private val _snackbarMessage = MutableSharedFlow<SnackbarMessage>(replay = 1)
    val snackbarMessage = _snackbarMessage.asSharedFlow()

    /**
     * 카테고리 설정
     */
    fun setCategory(category: Category) {
        _currentCategory.value = category
    }

    /**
     * 제목 업데이트
     */
    fun updatePostTitle(newTitle: String) {
        _boardTitle.value = newTitle
    }

    /**
     * 내용 업데이트
     */
    fun updatePostContent(newContent: String) {
        _boardContent.value = newContent
    }

    /**
     * Photo Picker에서 선택된 이미지 업데이트
     */
    fun onPhotoSelected(uris: List<Uri>) {
        _uiState.update { it.copy(selectedImageUris = uris) }
    }

    /**
     * 이미지의 X 버튼 클릭 시 해당 이미지 제거
     */
    fun onRemovePhoto(uri: Uri) {
        _uiState.update { state ->
            state.copy(
                selectedImageUris = state.selectedImageUris.filter { it != uri }
            )
        }
    }

    /**
     * 게시물 생성
     */
    fun createBoard() {
        // 필수 항목 미입력 시, 메시지 발행 후 종료
        getSnackbarMessage(_boardTitle.value, _boardContent.value)?.let { message ->
            viewModelScope.launch {
                _snackbarMessage.emit(message)
            }
            return
        }

        if (_uiState.value.isCreating) return

        viewModelScope.launch {
            // 로딩 시작
            _uiState.update { it.copy(isCreating = true) }

            try {
                val title = _boardTitle.value.trim()
                val content = _boardContent.value.trim()
                val category = _currentCategory.value.type.name
                val imageUris = _uiState.value.selectedImageUris

                createBoardUseCase(title, content, category, imageUris)
                    .onSuccess { boardId ->
                        _eventFlow.send(BoardWriteEvent.NavigateToDetail(boardId))
                    }
                    .onFailure { exception ->
                        Timber.e(exception, "게시물 생성 실패")
                    }
            } finally {
                _uiState.update { it.copy(isCreating = false) }
            }
        }
    }

    private fun getSnackbarMessage(title: String, content: String): SnackbarMessage? {
        if (title.isBlank()) {
            return SnackbarMessage(
                message = WriteMessage.TITLE_EMPTY
            )
        }
        if (content.isBlank()) {
            return SnackbarMessage(
                message = WriteMessage.CONTENT_EMPTY
            )
        }
        return null
    }
}
