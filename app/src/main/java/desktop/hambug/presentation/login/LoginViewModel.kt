package desktop.hambug.presentation.login

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.auth.KakaoLoginUseCase
import desktop.hambug.domain.usecase.fcm.SyncFcmTokenUseCase
import desktop.hambug.presentation.base.BaseViewModel
import desktop.hambug.util.ErrorHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

sealed class LoginEvent {
    data object NavigateToHome : LoginEvent()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val kakaoLoginUseCase: KakaoLoginUseCase,
    private val syncFcmTokenUseCase: SyncFcmTokenUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Default())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent: SharedFlow<LoginEvent> = _loginEvent.asSharedFlow()

    fun onKakaoLogin(context: Context) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Default(isProcessing = true)

            kakaoLoginUseCase(context)
                .onSuccess {
                    syncFcmTokenUseCase()
                        .onSuccess { Timber.d("로그인 후 FCM 토큰 동기화 성공") }
                        .onFailure { Timber.e(it, "로그인 후 FCM 토큰 동기화 실패") }

                    _uiState.value = LoginUiState.Default(isProcessing = false)
                    _loginEvent.emit(LoginEvent.NavigateToHome)
                }
                .onFailure { exception ->
                    _uiState.value = LoginUiState.Default(isProcessing = false)
                    handleError(exception)
                    _uiState.value = LoginUiState.Error
                }
        }
    }
}
