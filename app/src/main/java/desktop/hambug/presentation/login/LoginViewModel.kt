package desktop.hambug.presentation.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.KakaoLoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val kakaoLoginUseCase: KakaoLoginUseCase
) : ViewModel() {

    fun startKakaoLogin(context: Context) {
        viewModelScope.launch {
            runCatching {
                kakaoLoginUseCase(context)
            }.onSuccess { authorizationCode ->
                Log.d("kakao", "카카오 authorizationCode : $authorizationCode")
                // TODO: 인가코드를 API에 전달
            }.onFailure { error ->
                Log.e("kakao", "카카오로그인 실패 : ${error.message}", error)
            }
        }
    }
}
