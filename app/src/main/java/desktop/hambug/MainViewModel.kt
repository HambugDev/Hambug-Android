package desktop.hambug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.data.local.HambugTokenManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenManager: HambugTokenManager
) : ViewModel() {

    private val _logoutEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val logoutEvent: SharedFlow<Unit> = _logoutEvent.asSharedFlow()

    init {
        collectAuthStatus()
    }

    private fun collectAuthStatus() {
        viewModelScope.launch {
            tokenManager.authStatus.collectLatest { isLogoutRequired ->
                if (isLogoutRequired) {
                    _logoutEvent.emit(Unit)
                }
            }
        }
    }
}
