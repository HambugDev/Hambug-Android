package desktop.hambug.presentation.community

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Filter
import desktop.hambug.domain.model.FilterType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor() : ViewModel() {

    val filterList = listOf(
        Filter(1, "전체", FilterType.ALL),
        Filter(2, "자유잡담", FilterType.TALK),
        Filter(3, "햄버거리뷰", FilterType.REVIEW),
        Filter(4, "맛집추천", FilterType.RECOMMENDATION)
    )

    // 현재 선택된 필터 상태
    private val _currentFilter = MutableStateFlow(FilterType.ALL)
    val currentFilter: StateFlow<FilterType> = _currentFilter.asStateFlow()

    fun setFilter(filterType: FilterType) {
        _currentFilter.value = filterType
    }

    val isListView: StateFlow<Boolean> = currentFilter
        .map { filterType ->
            filterType == FilterType.ALL || filterType == FilterType.TALK
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = true)

    // 탭별 LazyList를 저장하는 Map
    // FilterType을 key로 사용하여 각 탭의 스크롤 위치 상태를 저장한다
    private val _scrollPositionStates = mutableMapOf<FilterType, LazyListState>()

    // 특정 FilterType에 대한 LazyListState를 가져온다
    // 상태가 없으면 새로 생성하여 Map에 저장 후 변환한다
    fun getScrollPositionForFilter(filterType: FilterType): LazyListState {
        return _scrollPositionStates.getOrPut(key = filterType) { LazyListState() }
    }
}
