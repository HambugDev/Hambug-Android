package desktop.hambug.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.GetHomeBurgersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getHomeBurgersUseCase: GetHomeBurgersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            getHomeBurgersUseCase()
                .onSuccess { burgers ->
                    Log.d("home", "getHomeBurgers 성공")
                    _uiState.value = HomeUiState.Success(burgers)
                }
                .onFailure { exception ->
                    Log.e("home", "getHomeBurgers 실패: ${exception.message}", exception)
                    val exceptionMessage = exception.message ?: "데이터 로딩 중 오류 발생"
                    _uiState.value = HomeUiState.Error(exceptionMessage)
                }
        }
    }
}
