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
        Category(1, "자유잡담", CategoryType.FREE_TALK),
        Category(2, "프랜차이즈", CategoryType.FRANCHISE),
        Category(3, "수제버거", CategoryType.HANDMADE),
        Category(4, "맛집추천", CategoryType.RECOMMENDATION)
    )

    private val _currentCategory = MutableStateFlow(CategoryType.FREE_TALK)
    val currentCategory: StateFlow<CategoryType> = _currentCategory.asStateFlow()

    fun setCategory(categoryType: CategoryType) {
        _currentCategory.value = categoryType
    }
}
