package desktop.hambug.presentation.noti

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.fcm.GetNotisUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotisUseCase: GetNotisUseCase
) : ViewModel() {

    init {
        loadNotis()
    }

    private fun loadNotis() {
        viewModelScope.launch {
            getNotisUseCase()
                .onSuccess { notis ->
                    Timber.d("notis: $notis")
                }
                .onFailure { exception ->
                    Timber.e(exception, "알림 목록 조회 실패")
                }
        }
    }
}
