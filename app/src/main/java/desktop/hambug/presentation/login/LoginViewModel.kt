package desktop.hambug.presentation.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.data.local.HambugTokenManager
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
    private val kakaoLoginUseCase: KakaoLoginUseCase,
    private val tokenManager: HambugTokenManager
) : ViewModel() {

    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent: SharedFlow<LoginEvent> = _loginEvent.asSharedFlow()

    fun onKakaoLogin(context: Context) {
        viewModelScope.launch {
            runCatching {
                kakaoLoginUseCase(context)
            }.onSuccess {
                Log.d("auth", "카카오 로그인 성공")

                checkSavedTokens()

                _loginEvent.emit(LoginEvent.NavigateToHome)
            }.onFailure { error ->
                Log.e("auth", "카카오 로그인 실패", error)
            }
        }
    }

    // datastore에 토큰이 저장되었는지 확인
    fun checkSavedTokens() {
        viewModelScope.launch {
            val accessToken = tokenManager.getAccessToken()
            val refreshToken = tokenManager.getRefreshToken()

            if (accessToken != null && refreshToken != null) {
                Log.d("token", "datastore - access token 수: ${accessToken.length}")
                Log.d("token", "datastore - refresh token 수: ${refreshToken.length}")
            } else {
                Log.e("token", "datastore에 토큰 저장 실패")
            }
        }
    }
}
