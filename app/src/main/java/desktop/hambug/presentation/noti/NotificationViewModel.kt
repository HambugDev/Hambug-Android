package desktop.hambug.presentation.noti

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.fcm.GetNotisUseCase
import desktop.hambug.presentation.base.BaseViewModel
import desktop.hambug.util.ErrorHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotisUseCase: GetNotisUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    private val _uiState = MutableStateFlow<NotiUiState>(NotiUiState.Loading)
    val uiState: StateFlow<NotiUiState> = _uiState.asStateFlow()

    init {
        loadNotis()
    }

    private fun loadNotis() {
        viewModelScope.launch {
            getNotisUseCase()
                .onSuccess { notis ->
                    // content 내부의 ": " -> ":\n" 으로 변환
                    val newNotis = notis.map { noti ->
                        noti.copy(content = noti.content.replace(": ", ":\n"))
                    }
                    _uiState.value = NotiUiState.Success(newNotis)
                }
                .onFailure { exception ->
                    handleError(exception)
                    _uiState.value = NotiUiState.Error
                }
        }
    }
}
