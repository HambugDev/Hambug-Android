package desktop.hambug.presentation.community

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Filter
import desktop.hambug.domain.model.FilterType
import desktop.hambug.domain.usecase.community.GetBoardsUseCase
import desktop.hambug.domain.usecase.community.GetCategoryBoardsUseCase
import desktop.hambug.presentation.base.BaseViewModel
import desktop.hambug.util.ErrorHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val getBoardsUseCase: GetBoardsUseCase,
    private val getCategoryBoardsUseCase: GetCategoryBoardsUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    // 현재 선택된 필터 상태
    private val _currentFilter = MutableStateFlow(FilterType.ALL)
    val currentFilter: StateFlow<FilterType> = _currentFilter.asStateFlow()

    // 현재 보여줄 UI 상태
    private val _currentUiState = MutableStateFlow<CommunityUiState>(CommunityUiState.Loading)
    val currentUiState: StateFlow<CommunityUiState> = _currentUiState.asStateFlow()

    // 현재 필터의 데이터 추가 조회 로딩 상태
    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore.asStateFlow()

    // 게시물 삭제 스낵바
    private val _showDeleteSnackbar = MutableStateFlow(false)
    val showDeleteSnackbar: StateFlow<Boolean> = _showDeleteSnackbar.asStateFlow()

    // 필터별 페이지네이션 상태 관리
    private val _paginationState = mutableMapOf<FilterType, PaginationState>()

    // 필터별 스크롤 위치 상태
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
        loadFilterData(FilterType.ALL)
    }

    /**
     * 특정 필터에 대한 LazyListState를 반환.
     * 상태가 없으면 새로 생성하여 저장한다
     */
    fun getScrollPositionForFilter(filterType: FilterType): LazyListState {
        return _scrollPositionStates.getOrPut(key = filterType) { LazyListState() }
    }

    /**
     * 필터 변경 시, 해당 필터의 데이터 조회
     */
    fun setFilter(filterType: FilterType) {
        _currentFilter.value = filterType

        val paginationState = _paginationState[filterType]

        if (paginationState != null && paginationState.boards.isNotEmpty()) {
            // 캐시된 데이터 있으면 표시
            _currentUiState.value = CommunityUiState.Success(paginationState.boards)
        } else {
            // 없으면 로딩 시작 후 데이터 로드
            _currentUiState.value = CommunityUiState.Loading
            loadFilterData(filterType)
        }
    }

    /**
     * 다음 페이지 로드
     */
    fun loadMoreBoards() {
        val currentFilterType = _currentFilter.value
        val paginationState = _paginationState[currentFilterType] ?: return

        // 이미 로딩 중이거나 다음 페이지가 없으면 반환
        if (_isLoadingMore.value || !paginationState.hasNextPage) {
            return
        }

        viewModelScope.launch {
            // 하단 로딩 시작
            _isLoadingMore.value = true

            val result = when (currentFilterType) {
                FilterType.ALL -> getBoardsUseCase(lastId = paginationState.nextCursorId)
                else -> {
                    val category = getCategoryString(currentFilterType)
                    getCategoryBoardsUseCase(category, lastId = paginationState.nextCursorId)
                }
            }

            result
                .onSuccess { page ->
                    // 페이지네이션 상태 업데이트
                    val updatedState = paginationState.updateWithNewPage(page)
                    _paginationState[currentFilterType] = updatedState

                    // 현재 보고있는 필터일때만 UI 업데이트
                    if (_currentFilter.value == currentFilterType) {
                        _currentUiState.value = CommunityUiState.Success(updatedState.boards)
                    }
                }
                .onFailure { exception ->
                    Timber.e(exception, "${currentFilterType.name} 필터의 데이터 추가 조회 실패")
                }

            // 로딩 종료
            _isLoadingMore.value = false
        }
    }

    /**
     * 특정 필터에 대한 초기 데이터 로드
     */
    private fun loadFilterData(filterType: FilterType) {
        // 이미 데이터가 있으면 생략
        val state = _paginationState[filterType]
        if (state != null && state.boards.isNotEmpty()) {
            return
        }

        viewModelScope.launch {
            val result = when (filterType) {
                FilterType.ALL -> getBoardsUseCase(lastId = null)
                else -> {
                    val category = getCategoryString(filterType)
                    getCategoryBoardsUseCase(category, lastId = null)
                }
            }

            result
                .onSuccess { page ->
                    _paginationState[filterType] = PaginationState(
                        boards = page.content,
                        nextCursorId = page.nextCursorId,
                        hasNextPage = page.nextPage
                    )

                    // 현재 보고있는 필터일때만 UI 업데이트
                    if (_currentFilter.value == filterType) {
                        _currentUiState.value = CommunityUiState.Success(page.content)
                    }
                }
                .onFailure { exception ->
                    Timber.e(exception, "${filterType.name} 필터의 데이터 조회 실패")
                    if (_currentFilter.value == filterType) {
                        handleError(exception)
                        _currentUiState.value = CommunityUiState.Error
                    }
                }
        }
    }

    private fun getCategoryString(filterType: FilterType): String {
        return when (filterType) {
            FilterType.ALL -> ""
            FilterType.TALK -> "FREE_TALK"
            FilterType.REVIEW -> "REVIEW"
            FilterType.RECOMMENDATION -> "RECOMMENDATION"
        }
    }

    /**
     * 모든 캐시 삭제, 현재 탭만 재조회
     */
    fun resetAllCache() {
        _paginationState.clear()
        val currentFilterType = _currentFilter.value
        _currentUiState.value = CommunityUiState.Loading
        loadFilterData(currentFilterType)

        // 스낵바 표시
        _showDeleteSnackbar.value = true
    }

    /**
     * 스낵바 표시 완료 처리
     */
    fun onFinishSnackbar() {
        _showDeleteSnackbar.value = false
    }

    /**
     * 현재 필터의 데이터만 갱신
     */
    fun refreshCurrentFilter() {
        val currentFilterType = _currentFilter.value
        _paginationState.remove(currentFilterType)
        _currentUiState.value = CommunityUiState.Loading
        loadFilterData(currentFilterType)
    }
}
