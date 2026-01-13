package desktop.hambug.presentation.community

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.community.ReportUseCase
import desktop.hambug.presentation.common.SnackbarMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

sealed interface ReportEvent {
    data object NavigateToDetail : ReportEvent
}

@HiltViewModel
class ReportViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val reportUseCase: ReportUseCase
) : ViewModel() {

    // 내비게이션 인자 추출
    private val reportType: String = checkNotNull(savedStateHandle["reportType"])
    private val targetId: Int = checkNotNull(savedStateHandle["targetId"])

    private val _uiState = MutableStateFlow(ReportUiState())
    val uiState: StateFlow<ReportUiState> = _uiState.asStateFlow()

    private val _reportContent = MutableStateFlow("")
    val reportContent: StateFlow<String> = _reportContent.asStateFlow()

    private val _eventFlow = Channel<ReportEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private val _snackbarMessage = MutableSharedFlow<SnackbarMessage>(replay = 1)
    val snackbarMessage = _snackbarMessage.asSharedFlow()

    /**
     * 내용 업데이트
     */
    fun updateReportContent(newContent: String) {
        _reportContent.value = newContent
    }

    /**
     * 게시물/댓글 신고
     */
    fun submitReport() {
        getSnackbarMessage(_reportContent.value)?.let { message ->
            viewModelScope.launch {
                _snackbarMessage.emit(message)
            }
            return
        }

        if (_uiState.value.isReporting) return

        viewModelScope.launch {
            _uiState.update { it.copy(isReporting = true) }

            try {
                val content = _reportContent.value.trim()

                reportUseCase(targetId, reportType, content)
                    .onSuccess {
                        _eventFlow.send(ReportEvent.NavigateToDetail)
                    }
                    .onFailure { exception ->
                        Timber.e(exception, "게시물/댓글 신고 실패")
                    }
            } finally {
                _uiState.update { it.copy(isReporting = false) }
            }
        }
    }

    private fun getSnackbarMessage(content: String): SnackbarMessage? {
        return when {
            content.isBlank() -> SnackbarMessage(message = WriteMessage.CONTENT_EMPTY)
            else -> null
        }
    }
}
