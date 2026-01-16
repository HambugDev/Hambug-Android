package desktop.hambug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.data.local.HambugTokenManager
import desktop.hambug.util.VersionManager
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
    private val tokenManager: HambugTokenManager,
    private val versionManager: VersionManager
) : ViewModel() {

    private val _showNativeSplash = MutableStateFlow(true)
    val showNativeSplash: StateFlow<Boolean> = _showNativeSplash.asStateFlow()

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination.asStateFlow()

    private val _logoutEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val logoutEvent: SharedFlow<Unit> = _logoutEvent.asSharedFlow()

    private val _needForceUpdate = MutableStateFlow(false)
    val needForceUpdate: StateFlow<Boolean> = _needForceUpdate.asStateFlow()

    companion object {
        private const val CUSTOM_SPLASH_DURATION = 1500L
    }

    init {
        checkAppVersion()
        observeLogoutEvent()
    }

    private fun checkAppVersion() {
        viewModelScope.launch {
            versionManager.getVersionInfo()

            if (versionManager.isForceUpdateRequired()) {
                _needForceUpdate.value = true
                // Native Splash가 Dialog를 가리지 않도록 설정
                _showNativeSplash.value = false
                return@launch
            }

            // 업데이트 필요 없으면 기존 로직 진행
            checkLoginStatus()
        }
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
