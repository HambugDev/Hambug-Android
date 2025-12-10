package desktop.hambug.presentation.community

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Board
import desktop.hambug.domain.model.BoardPage
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

    // 페이지네이션 로딩 상태
    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore.asStateFlow()

    // 페이지네이션 관련 데이터
    private var nextCursorId: Int? = null
    private var hasNextPage: Boolean = true

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
     * 전체 데이터 로드 (페이지네이션 적용)
     */
    private fun loadAllData() {
        nextCursorId = null
        hasNextPage = true

        viewModelScope.launch {
            getBoardsUseCase(lastId = null)
                .onSuccess { page ->
                    Log.d("community", "loadAllData 성공")
                    updatePaginationState(page)

                    _cachedBoards[FilterType.ALL] = page.content

                    // 현재 보고있는 필터가 ALL일 때만 UI 업데이트
                    if (_currentFilter.value == FilterType.ALL) {
                        _currentUiState.value = CommunityUiState.Success(page.content)
                    }
                }
                .onFailure { exception ->
                    Log.e("community", "loadAllData 실패: ${exception.message}", exception)
                    if (_currentFilter.value == FilterType.ALL) {
                        val message = exception.message ?: "전체 데이터 로드 실패"
                        _currentUiState.value = CommunityUiState.Error(message)
                    }
                }
        }
    }

    /**
     * 다음 페이지 로드
     */
    fun loadMoreBoards() {
        // 이미 로딩 중이거나, 다음 페이지가 없거나, 현재 필터가 ALL이 아니면 반환
        if (_isLoadingMore.value || !hasNextPage || _currentFilter.value != FilterType.ALL) return

        viewModelScope.launch {
            _isLoadingMore.value = true

            getBoardsUseCase(lastId = nextCursorId)
                .onSuccess { page ->
                    updatePaginationState(page)

                    // 기존 데이터 뒤에 새 데이터 붙이기
                    val currentList = _cachedBoards[FilterType.ALL] ?: emptyList()
                    val newList = currentList + page.content

                    _cachedBoards[FilterType.ALL] = newList

                    if (_currentFilter.value == FilterType.ALL) {
                        _currentUiState.value = CommunityUiState.Success(newList)
                    }
                }
                .onFailure {
                    Log.e("community", "추가 로드 실패: ${it.message}")
                }

            _isLoadingMore.value = false
        }
    }

    private fun updatePaginationState(page: BoardPage) {
        nextCursorId = page.nextCursorId
        hasNextPage = page.nextPage
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
