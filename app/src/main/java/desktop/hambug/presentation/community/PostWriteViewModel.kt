package desktop.hambug.presentation.community

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Category
import desktop.hambug.domain.model.CategoryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostWriteViewModel @Inject constructor() : ViewModel() {

    val categoryList = listOf(
        Category(1, "자유잡담", "자유롭게 이야기를 나눠보세요", CategoryType.FREE_TALK),
        Category(2, "프랜차이즈", "프랜차이즈 햄버거 경험을 공유해주세요", CategoryType.FRANCHISE),
        Category(3, "수제버거", "수제버거 경험을 공유해주세요", CategoryType.HANDMADE),
        Category(4, "맛집추천", "햄버거 맛집 정보를 추천해주세요", CategoryType.RECOMMENDATION)
    )

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
}
