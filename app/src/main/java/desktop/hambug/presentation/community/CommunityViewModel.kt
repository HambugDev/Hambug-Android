package desktop.hambug.presentation.community

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Board
import desktop.hambug.domain.model.Filter
import desktop.hambug.domain.model.FilterType
import desktop.hambug.domain.usecase.GetBoardsUseCase
import desktop.hambug.domain.usecase.GetCategoryBoardsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getBoardsUseCase: GetBoardsUseCase,
    private val getCategoryBoardsUseCase: GetCategoryBoardsUseCase
) : ViewModel() {

    // 현재 선택된 필터 상태
    private val _currentFilter = MutableStateFlow(FilterType.ALL)
    val currentFilter: StateFlow<FilterType> = _currentFilter.asStateFlow()

    // 현재 보여줄 UI 상태
    private val _currentUiState = MutableStateFlow<CommunityUiState>(CommunityUiState.Loading)
    val currentUiState: StateFlow<CommunityUiState> = _currentUiState.asStateFlow()

    // 각 필터별 캐시된 데이터
    private val _cachedBoards = mutableMapOf<FilterType, List<Board>>()

    // 각 필터별 스크롤 위치 상태
    private val _scrollPositionStates = mutableMapOf<FilterType, LazyListState>()

    val filterList = listOf(
        Filter(1, "전체", FilterType.ALL),
        Filter(2, "자유잡담", FilterType.TALK),
        Filter(3, "햄버거리뷰", FilterType.REVIEW),
        Filter(4, "맛집추천", FilterType.RECOMMENDATION)
    )

    val isListView: StateFlow<Boolean> = currentFilter
        .map { filterType ->
            filterType == FilterType.ALL || filterType == FilterType.TALK
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = true)

    init {
        loadAllData()
    }

    /**
     * 특정 필터에 대한 LazyListState를 반환
     * 상태가 없으면 새로 생성하여 저장한다
     */
    fun getScrollPositionForFilter(filterType: FilterType): LazyListState {
        return _scrollPositionStates.getOrPut(key = filterType) { LazyListState() }
    }

    /**
     * 필터 변경 시, 해당 필터의 데이터 로드 및 UI 상태 업데이트
     */
    fun setFilter(filterType: FilterType) {
        _currentFilter.value = filterType

        // 캐시된 데이터가 있으면 즉시 표시
        val cachedData = _cachedBoards[filterType]
        if (cachedData != null) {
            Log.d("community", "${filterType.name} 탭: 캐시된 데이터 사용")
            _currentUiState.value = CommunityUiState.Success(cachedData)
        } else {
            // 캐시가 없으면 로딩 시작 후 데이터 로드
            _currentUiState.value = CommunityUiState.Loading
            loadFilterData(filterType)
        }
    }

    /**
     * 전체 데이터 로드
     */
    private fun loadAllData() {
        val filterType = FilterType.ALL

        viewModelScope.launch {
            getBoardsUseCase()
                .onSuccess { boards ->
                    Log.d("community", "loadAllData 성공")
                    _cachedBoards[filterType] = boards

                    // 현재 보고있는 필터가 ALL일 때만 UI 업데이트
                    if (_currentFilter.value == filterType) {
                        _currentUiState.value = CommunityUiState.Success(boards)
                    }
                }
                .onFailure { exception ->
                    Log.e("community", "loadAllData 실패: ${exception.message}", exception)
                    if (_currentFilter.value == filterType) {
                        val message = exception.message ?: "커뮤니티 데이터 로딩 실패"
                        _currentUiState.value = CommunityUiState.Error(message)
                    }
                }
        }
    }

    /**
     * 특정 필터에 대한 데이터 로드
     */
    private fun loadFilterData(filterType: FilterType) {
        if (filterType == FilterType.ALL) return

        // 이미 캐시된 데이터가 있으면 API 호출 생략
        if (_cachedBoards.containsKey(filterType)) {
            Log.d("community", "${filterType.name} 탭: API 호출 생략")
            return
        }

        viewModelScope.launch {
            val category = when (filterType) {
                FilterType.ALL -> ""
                FilterType.TALK -> "FREE_TALK"
                // 수정 필요
                FilterType.REVIEW -> "FRANCHISE"
                FilterType.RECOMMENDATION -> "RECOMMENDATION"
            }

            getCategoryBoardsUseCase(category)
                .onSuccess { boards ->
                    Log.d("community", "${filterType.name} 카테고리 데이터 로드 성공")
                    _cachedBoards[filterType] = boards

                    // 현재 보고있는 필터가 해당 필터일 때만 UI 업데이트
                    if (_currentFilter.value == filterType) {
                        _currentUiState.value = CommunityUiState.Success(boards)
                    }
                }
                .onFailure { exception ->
                    Log.e("community", "loadFilterData 실패: ${exception.message}", exception)
                    if (_currentFilter.value == filterType) {
                        val message = exception.message ?: "카테고리 데이터 로딩 실패"
                        _currentUiState.value = CommunityUiState.Error(message)
                    }
                }
        }
    }
}
