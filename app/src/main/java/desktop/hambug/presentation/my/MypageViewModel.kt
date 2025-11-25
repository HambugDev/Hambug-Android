package desktop.hambug.presentation.my

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.GetUserInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyUiState>(MyUiState.Loading)
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    init {
        loadMydata()
    }

    private fun loadMydata() {
        viewModelScope.launch {
            getUserInfoUseCase()
                .onSuccess { userInfo ->
                    Log.d("my", "getUserInfo 성공")
                    _uiState.value = MyUiState.Success(userInfo)
                }
                .onFailure { exception ->
                    Log.e("my", "getUserInfo 실패: ${exception.message}", exception)
                    val exceptionMessage = exception.message ?: "데이터 로딩 중 오류 발생"
                    _uiState.value = MyUiState.Error(exceptionMessage)
                }
        }
    }
}
