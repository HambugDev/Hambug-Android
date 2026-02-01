package desktop.hambug.presentation.community

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.community.ReportUseCase
import desktop.hambug.presentation.base.BaseViewModel
import desktop.hambug.presentation.component.snackbar.SnackbarMessage
import desktop.hambug.util.ErrorHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface ReportEvent {
    data object NavigateToDetail : ReportEvent
}

@HiltViewModel
class ReportViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val reportUseCase: ReportUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    // 내비게이션 인자 추출
    private val reportType: String = checkNotNull(savedStateHandle["reportType"])
    private val targetId: Int = checkNotNull(savedStateHandle["targetId"])

    private val _uiState = MutableStateFlow<ReportUiState>(ReportUiState.Default())
    val uiState: StateFlow<ReportUiState> = _uiState.asStateFlow()

    private val _reportTitle = MutableStateFlow("")
    val reportTitle: StateFlow<String> = _reportTitle.asStateFlow()

    private val _reportContent = MutableStateFlow("")
    val reportContent: StateFlow<String> = _reportContent.asStateFlow()

    private val _eventFlow = Channel<ReportEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private val _snackbarMessage = MutableSharedFlow<SnackbarMessage>(replay = 1)
    val snackbarMessage = _snackbarMessage.asSharedFlow()

    /**
     * 제목 업데이트
     */
    fun updateReportTitle(newTitle: String) {
        _reportTitle.value = newTitle
    }

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
        getSnackbarMessage(_reportTitle.value, _reportContent.value)?.let { message ->
            viewModelScope.launch {
                _snackbarMessage.emit(message)
            }
            return
        }

        if (_uiState.value == ReportUiState.Default(isReporting = true)) return

        viewModelScope.launch {
            _uiState.value = ReportUiState.Default(isReporting = true)

            try {
                val title = _reportTitle.value.trim()
                val content = _reportContent.value.trim()

                reportUseCase(targetId, reportType, title, content)
                    .onSuccess {
                        _eventFlow.send(ReportEvent.NavigateToDetail)
                    }
                    .onFailure { exception ->
                        handleError(exception)
                        _uiState.value = ReportUiState.Error
                    }
            } finally {
                _uiState.value = ReportUiState.Default(isReporting = false)
            }
        }
    }

    private fun getSnackbarMessage(title: String, content: String): SnackbarMessage? {
        return when {
            title.isBlank() -> SnackbarMessage(message = WriteMessage.TITLE_EMPTY)
            content.isBlank() -> SnackbarMessage(message = WriteMessage.CONTENT_EMPTY)
            else -> null
        }
    }
}
