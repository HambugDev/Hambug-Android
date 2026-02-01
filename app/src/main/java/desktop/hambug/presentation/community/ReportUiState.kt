package desktop.hambug.presentation.community

sealed class ReportUiState {
    data class Default(val isReporting: Boolean = false) : ReportUiState()
    data object Error : ReportUiState()
}
