package desktop.hambug.presentation.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.KakaoLoginUseCase
import desktop.hambug.domain.usecase.fcm.SyncFcmTokenUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginEvent {
    data object NavigateToHome : LoginEvent()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val kakaoLoginUseCase: KakaoLoginUseCase,
    private val syncFcmTokenUseCase: SyncFcmTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent: SharedFlow<LoginEvent> = _loginEvent.asSharedFlow()

    fun onKakaoLogin(context: Context) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            kakaoLoginUseCase(context)
                .onSuccess {
                    syncFcmTokenUseCase()
                        .onSuccess { Log.d("fcm", "로그인 후 FCM 토큰 동기화 성공") }
                        .onFailure { Log.e("fcm", "로그인 후 FCM 토큰 동기화 실패", it) }

                    _uiState.update { it.copy(isLoading = false) }
                    _loginEvent.emit(LoginEvent.NavigateToHome)
                }
                .onFailure { exception ->
                    Log.e("auth", "카카오 로그인 실패: ${exception.message}", exception)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "로그인에 실패했습니다"
                        )
                    }
                }
        }
    }
}
