package desktop.hambug.presentation.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.KakaoLoginUseCase
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
    private val kakaoLoginUseCase: KakaoLoginUseCase
) : ViewModel() {

    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent: SharedFlow<LoginEvent> = _loginEvent.asSharedFlow()

    fun onKakaoLogin(context: Context) {
        viewModelScope.launch {
            runCatching {
                kakaoLoginUseCase(context)
            }.onSuccess {
                Log.d("auth", "카카오 로그인 성공")
                _loginEvent.emit(LoginEvent.NavigateToHome)
            }.onFailure { error ->
                Log.e("kakao", "카카오 로그인 실패", error)
            }
        }
    }
}
