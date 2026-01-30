package desktop.hambug.presentation.base

import androidx.lifecycle.ViewModel
import desktop.hambug.util.ErrorHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel(
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    protected suspend fun handleError(throwable: Throwable) {
        val message = errorHandler.getErrorMessage(throwable)
        _errorMessage.emit(message)
    }
}
