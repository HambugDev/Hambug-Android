package desktop.hambug.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.GetHomeBoardsUseCase
import desktop.hambug.domain.usecase.GetHomeBurgersUseCase
import desktop.hambug.domain.usecase.fcm.SyncFcmTokenUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeBurgersUseCase: GetHomeBurgersUseCase,
    private val getHomeBoardsUseCase: GetHomeBoardsUseCase,
    private val syncFcmTokenUseCase: SyncFcmTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var isFcmTokenSynced = false

    init {
        syncFcmToken()
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            val deferredBurgers = async { getHomeBurgersUseCase() }
            val deferredBoards = async { getHomeBoardsUseCase() }

            val burgersResult = deferredBurgers.await()
            val boardsResult = deferredBoards.await()

            if (burgersResult.isSuccess && boardsResult.isSuccess) {
                val burgers = burgersResult.getOrThrow()
                val boards = boardsResult.getOrThrow()
                _uiState.value = HomeUiState.Success(burgers, boards)
            } else {
                val errorMessage = when {
                    burgersResult.isFailure && boardsResult.isFailure ->
                        "데이터 로드 실패"
                    burgersResult.isFailure -> "버거 정보 로드 실패"
                    else -> "인기글 정보 로드 실패"
                }
                _uiState.value = HomeUiState.Error(errorMessage)
            }
        }
    }

    /**
     * FCM 토큰 동기화
     */
    fun syncFcmToken() {
        if (isFcmTokenSynced) {
            Log.d("fcm", "홈 - FCM 토큰 동기화 패스")
            return
        }

        viewModelScope.launch {
            syncFcmTokenUseCase()
                .onSuccess {
                    isFcmTokenSynced = true
                    Log.d("fcm", "홈 진입 시 FCM 토큰 동기화 성공")
                }
                .onFailure { exception ->
                    Log.e("fcm", "홈 진입 시 FCM 토큰 동기화 실패", exception)
                }
        }
    }
}
