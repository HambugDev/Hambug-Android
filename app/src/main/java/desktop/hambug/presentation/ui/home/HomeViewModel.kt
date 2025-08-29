package desktop.hambug.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.model.Burger
import desktop.hambug.domain.usecase.GetBurgerListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBurgerListUseCase: GetBurgerListUseCase
) : ViewModel() {

    private val _burgers = MutableStateFlow<List<Burger>>(emptyList())
    val burgers: StateFlow<List<Burger>> = _burgers

    init {
        getBurgers()
    }

    private fun getBurgers() {
        viewModelScope.launch {
            _burgers.value = getBurgerListUseCase.invoke()
        }
    }
}
