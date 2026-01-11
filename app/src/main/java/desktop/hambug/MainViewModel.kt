package desktop.hambug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.data.local.HambugTokenManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenManager: HambugTokenManager
) : ViewModel() {

    private val _showNativeSplash = MutableStateFlow(true)
    val showNativeSplash: StateFlow<Boolean> = _showNativeSplash.asStateFlow()

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination.asStateFlow()

    private val _logoutEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val logoutEvent: SharedFlow<Unit> = _logoutEvent.asSharedFlow()

    companion object {
        private const val CUSTOM_SPLASH_DURATION = 1500L
    }

    init {
        checkLoginStatus()
        observeLogoutEvent()
    }

    // 앱 시작 시 토큰 유무 확인
    private fun checkLoginStatus() {
        viewModelScope.launch {
            _showNativeSplash.value = false

            val startTime = System.currentTimeMillis()

            val isLogin = tokenManager.isLogin()

            val elapsedTime = System.currentTimeMillis() - startTime
            val remainingTime = CUSTOM_SPLASH_DURATION - elapsedTime
            if (remainingTime > 0) {
                delay(remainingTime)
            }

            _startDestination.value = if (isLogin) "main_graph" else "login"
        }
    }

    private fun observeLogoutEvent() {
        viewModelScope.launch {
            tokenManager.logoutEvent.collectLatest {
                _logoutEvent.emit(Unit)
            }
        }
    }
}
