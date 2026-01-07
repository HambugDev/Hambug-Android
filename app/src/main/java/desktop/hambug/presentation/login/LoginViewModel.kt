package desktop.hambug.presentation.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.KakaoLoginUseCase
import desktop.hambug.domain.usecase.fcm.SyncFcmTokenUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent: SharedFlow<LoginEvent> = _loginEvent.asSharedFlow()

    fun onKakaoLogin(context: Context) {
        viewModelScope.launch {
            runCatching {
                kakaoLoginUseCase(context)
            }.onSuccess {
                Log.d("auth", "카카오 로그인 성공")

                syncFcmTokenUseCase()
                    .onSuccess {
                        Log.d("fcm", "로그인 후 FCM 토큰 동기화 성공")
                    }
                    .onFailure { exception ->
                        Log.e("fcm", "로그인 후 FCM 토큰 동기화 실패", exception)
                    }

                _loginEvent.emit(LoginEvent.NavigateToHome)
            }.onFailure { error ->
                Log.e("auth", "카카오 로그인 실패", error)
            }
        }
    }
}
