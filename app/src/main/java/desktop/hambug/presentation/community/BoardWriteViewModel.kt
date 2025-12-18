package desktop.hambug.presentation.community

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Category
import desktop.hambug.domain.model.CategoryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BoardWriteViewModel @Inject constructor() : ViewModel() {

    val categoryList = listOf(
        Category(1, "자유잡담", "자유롭게 이야기를 나눠보세요", CategoryType.FREE_TALK),
        Category(2, "프랜차이즈", "프랜차이즈 햄버거 경험을 공유해주세요", CategoryType.FRANCHISE),
        Category(3, "수제버거", "수제버거 경험을 공유해주세요", CategoryType.HANDMADE),
        Category(4, "맛집추천", "햄버거 맛집 정보를 추천해주세요", CategoryType.RECOMMENDATION)
    )

    private val _uiState = MutableStateFlow(BoardWriteUiState())
    val uiState: StateFlow<BoardWriteUiState> = _uiState.asStateFlow()

    private val _currentCategory = MutableStateFlow(categoryList[0])
    val currentCategory: StateFlow<Category> = _currentCategory.asStateFlow()

    // 게시물 제목 상태
    private val _postTitle = MutableStateFlow("")
    val postTitle: StateFlow<String> = _postTitle.asStateFlow()
    // 게시물 내용 상태
    private val _postContent = MutableStateFlow("")
    val postContent: StateFlow<String> = _postContent.asStateFlow()

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
        _postTitle.value = newTitle
    }

    /**
     * 내용 업데이트
     */
    fun updatePostContent(newContent: String) {
        _postContent.value = newContent
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
}
