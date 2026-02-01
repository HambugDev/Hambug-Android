package desktop.hambug.presentation.community

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Category
import desktop.hambug.domain.model.CategoryType
import desktop.hambug.domain.usecase.community.CreateBoardUseCase
import desktop.hambug.domain.usecase.community.GetBoardDetailUseCase
import desktop.hambug.domain.usecase.community.UpdateBoardUseCase
import desktop.hambug.presentation.base.BaseViewModel
import desktop.hambug.presentation.component.snackbar.SnackbarMessage
import desktop.hambug.util.ErrorHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface BoardWriteEvent {
    data class CreateSuccess(val boardId: Int) : BoardWriteEvent
    data class UpdateSuccess(val boardId: Int) : BoardWriteEvent
}

@HiltViewModel
class BoardWriteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createBoardUseCase: CreateBoardUseCase,
    private val boardDetailUseCase: GetBoardDetailUseCase,
    private val updateBoardUseCase: UpdateBoardUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    val categoryList = listOf(
        Category(1, "자유잡담", "자유롭게 이야기를 나눠보세요", CategoryType.FREE_TALK),
//        Category(2, "프랜차이즈", "프랜차이즈 햄버거 경험을 공유해주세요", CategoryType.REVIEW),
//        Category(3, "수제버거", "수제버거 경험을 공유해주세요", CategoryType.REVIEW),
        Category(2, "햄버거리뷰", "프랜차이즈/수제버거 경험을 공유해주세요", CategoryType.REVIEW),
        Category(3, "맛집추천", "햄버거 맛집 정보를 추천해주세요", CategoryType.RECOMMENDATION)
    )

    private val boardId: Int = savedStateHandle["boardId"] ?: -1
    val isEditMode = boardId != -1

    private val _uiState = MutableStateFlow<BoardWriteUiState>(BoardWriteUiState.Loading)
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

    init {
        if (isEditMode) {
            loadBoardInfo()
        } else {
            _uiState.value = BoardWriteUiState.Success()
        }
    }

    private fun loadBoardInfo() {
        viewModelScope.launch {
            boardDetailUseCase(boardId)
                .onSuccess { board ->
                    _uiState.value = BoardWriteUiState.Success(
                        existingImageUrls = board.imageUrls
                    )

                    _boardTitle.value = board.title
                    _boardContent.value = board.content

                    // 카테고리 매칭
                    val category = categoryList.find { it.type.name == board.category } ?: categoryList[0]
                    _currentCategory.value = category
                }
                .onFailure { exception ->
                    handleError(exception)
                    _uiState.value = BoardWriteUiState.Error
                }
        }
    }

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
     * Photo Picker에서 선택된 이미지 추가
     */
    fun addImages(uris: List<Uri>) {
        val state = _uiState.value
        if (state is BoardWriteUiState.Success) {
            // 총 이미지 개수 (기본 URL + 이미 선택된 URI)
            val totalCnt = state.existingImageUrls.size + state.selectedImageUris.size
            // 추가 가능한 개수
            val remainSpace = 5 - totalCnt

            // 추가 가능한 만큼만 잘라서 합치기
            if (remainSpace > 0) {
                val newImages = uris.take(remainSpace)
                _uiState.value = state.copy(
                    selectedImageUris = state.selectedImageUris + newImages
                )
            }
        }
    }

    /**
     * 새로 선택한 이미지 제거 (URI)
     */
    fun onRemoveNewImage(uri: Uri) {
        val state = _uiState.value
        if (state is BoardWriteUiState.Success) {
            _uiState.value = state.copy(
                selectedImageUris = state.selectedImageUris.filter { it != uri }
            )
        }
    }

    /**
     * 기존 이미지 제거 (URL)
     */
    fun onRemoveOldImage(url: String) {
        val state = _uiState.value
        if (state is BoardWriteUiState.Success) {
            _uiState.value = state.copy(
                existingImageUrls = state.existingImageUrls.filter { it != url }
            )
        }
    }

    /**
     * 게시물 생성 또는 수정
     */
    fun saveBoard() {
        // 필수 항목 미입력 시, 메시지 발행 후 종료
        getSnackbarMessage(_boardTitle.value, _boardContent.value)?.let { message ->
            viewModelScope.launch {
                _snackbarMessage.emit(message)
            }
            return
        }

        val state = _uiState.value
        if (state !is BoardWriteUiState.Success) return

        // 저장 시작
        _uiState.value = state.copy(isSaving = true)

        viewModelScope.launch {
            try {
                val title = _boardTitle.value.trim()
                val content = _boardContent.value.trim()
                val category = _currentCategory.value.type.name
                val oldImageUrls = state.existingImageUrls
                val newImageUris = state.selectedImageUris

                if (isEditMode) {
                    // 수정
                    updateBoardUseCase(
                        boardId = boardId,
                        title = title,
                        content = content,
                        category = category,
                        oldImageUrls = oldImageUrls,
                        newImageUris = newImageUris
                    ).onSuccess {
                        _eventFlow.send(BoardWriteEvent.UpdateSuccess(boardId))
                    }.onFailure { exception ->
                        handleError(exception)
                        _uiState.value = BoardWriteUiState.Error
                    }
                } else {
                    // 생성
                    createBoardUseCase(title, content, category, newImageUris)
                        .onSuccess { boardId ->
                            _eventFlow.send(BoardWriteEvent.CreateSuccess(boardId))
                        }
                        .onFailure { exception ->
                            handleError(exception)
                            _uiState.value = BoardWriteUiState.Error
                        }
                }
            } finally {
                _uiState.value = state.copy(isSaving = false)
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
