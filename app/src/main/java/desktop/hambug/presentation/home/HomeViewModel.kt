package desktop.hambug.presentation.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.HomeBoard
import desktop.hambug.domain.model.HomeBurger
import desktop.hambug.domain.usecase.home.GetHomeBoardsUseCase
import desktop.hambug.domain.usecase.home.GetHomeBurgersUseCase
import desktop.hambug.domain.usecase.fcm.SyncFcmTokenUseCase
import desktop.hambug.presentation.base.BaseViewModel
import desktop.hambug.util.ErrorHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeBurgersUseCase: GetHomeBurgersUseCase,
    private val getHomeBoardsUseCase: GetHomeBoardsUseCase,
    private val syncFcmTokenUseCase: SyncFcmTokenUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var isFcmTokenSynced = false

    init {
        syncFcmToken()
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            try {
                val deferredBurgers = async { getHomeBurgersUseCase() }
                val deferredBoards = async { getHomeBoardsUseCase() }

                val burgersResult = deferredBurgers.await()
                val boardsResult = deferredBoards.await()

                if (burgersResult.isSuccess && boardsResult.isSuccess) {
                    val burgers = burgersResult.getOrThrow()
                    val boards = boardsResult.getOrThrow()
                    _uiState.value = HomeUiState.Success(burgers, boards)
                } else {
                    handleLoadError(burgersResult, boardsResult)
                }
            } catch (e: Exception) {
                handleError(e)
                _uiState.value = HomeUiState.Error
            }
        }
    }

    private fun handleLoadError(
        burgersResult: Result<List<HomeBurger>>,
        boardsResult: Result<List<HomeBoard>>
    ) {
        when {
            burgersResult.isFailure && boardsResult.isFailure -> {
                Timber.e("home 데이터 조회 실패")
            }
            burgersResult.isFailure -> {
                Timber.e("home 추천버거 조회 실패")
            }
            else -> {
                Timber.e("home 인기글 조회 실패")
            }
        }
        _uiState.value = HomeUiState.Error
    }

    /**
     * FCM 토큰 동기화
     */
    private fun syncFcmToken() {
        if (isFcmTokenSynced) {
            Timber.d("FCM 토큰 이미 동기화됨")
            return
        }

        viewModelScope.launch {
            syncFcmTokenUseCase()
                .onSuccess {
                    isFcmTokenSynced = true
                    Timber.d("FCM 토큰 동기화 성공")
                }
                .onFailure { exception ->
                    Timber.e(exception, "FCM 토큰 동기화 실패")
                }
        }
    }
}
