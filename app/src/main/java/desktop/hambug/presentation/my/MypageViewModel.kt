package desktop.hambug.presentation.my

import android.net.Uri
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import desktop.hambug.domain.usecase.my.GetUserInfoUseCase
import desktop.hambug.domain.usecase.my.UpdateUserNicknameUseCase
import desktop.hambug.domain.usecase.my.UpdateUserProfileImageUseCase
import desktop.hambug.domain.usecase.auth.LogoutUseCase
import desktop.hambug.domain.usecase.auth.UnlinkUseCase
import desktop.hambug.presentation.base.BaseViewModel
import desktop.hambug.util.ErrorHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

sealed class MypageEvent {
    data object LaunchPhotoPicker : MypageEvent()
}

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val updateUserNicknameUseCase: UpdateUserNicknameUseCase,
    private val updateUserProfileImageUseCase: UpdateUserProfileImageUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val unlinkUseCase: UnlinkUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    private val _uiState = MutableStateFlow<MyUiState>(MyUiState.Loading)
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    private val _nicknameState = MutableStateFlow(NicknameValidationState())
    val nicknameState: StateFlow<NicknameValidationState> = _nicknameState

    private val _isUnlinking = MutableStateFlow(false)
    val isUnlinking = _isUnlinking.asStateFlow()

    // Photo Picker 실행 이벤트
    private val _mypageEvent = MutableSharedFlow<MypageEvent>()
    val mypageEvent: SharedFlow<MypageEvent> = _mypageEvent.asSharedFlow()

    private val MIN_LENGTH = 2
    private val MAX_LENGTH = 10

    // 한글, 영문, 숫자, .(마침표), _(언더바)만 허용
    private val ALLOWD_CHARS_REGEX = Regex("^[가-힣a-zA-Z0-9._]*$")

    init {
        loadMydata()
    }

    private fun loadMydata() {
        viewModelScope.launch {
            getUserInfoUseCase()
                .onSuccess { userInfo ->
                    _uiState.value = MyUiState.Success(userInfo)
                }
                .onFailure { exception ->
                    handleError(exception)
                    _uiState.value = MyUiState.Error
                }
        }
    }

    /**
     * 닉네임 검사 및 업데이트
     */
    fun onNicknameChange(newInput: String) {

        // 10자 초과 입력 차단
        val finalInput = if (newInput.length > MAX_LENGTH) {
            newInput.substring(0, MAX_LENGTH)
        } else {
            newInput
        }

        val (isValid, errorMessage) = validateNickname(finalInput)

        _nicknameState.update {
            it.copy(
                currentNickname = finalInput,
                errorMessage = errorMessage,
                isValid = isValid
            )
        }
    }

    private fun validateNickname(nickname: String): Pair<Boolean, String?> {

        // 빈 문자열인 경우
        if (nickname.isBlank()) {
            return Pair(false, null)
        }

        // 문자 검사
        if (!nickname.matches(ALLOWD_CHARS_REGEX)) {
            return Pair(false, "한글, 영문, 숫자, .(마침표), _(언더바)만 가능합니다")
        }

        // 글자수 검사
        if (nickname.length < MIN_LENGTH) {
            return Pair(false, "2~10자로 입력해주세요")
        }

        // 모든 조건 통과
        return Pair(true, null)
    }

    /**
     * 닉네임 변경
     */
    fun updateUserNickname(onSuccess: () -> Unit) {

        val currentUiState = _uiState.value
        val currentNicknameState = _nicknameState.value

        if (currentUiState is MyUiState.Success) {
            if (!currentNicknameState.isValid || currentNicknameState.isSaving || currentNicknameState.currentNickname.isEmpty()) {
                return
            }

            // 저장 시작
            _nicknameState.update { it.copy(isSaving = true) }

            val userId = currentUiState.userInfo.userId
            val newNickname = currentNicknameState.currentNickname

            viewModelScope.launch {
                updateUserNicknameUseCase(userId = userId, nickname = newNickname)
                    .onSuccess { userInfo ->
                        _uiState.value = MyUiState.Success(userInfo)
                        onSuccess()
                    }
                    .onFailure { exception ->
                        handleError(exception)
                        _uiState.value = MyUiState.Error
                    }

                _nicknameState.update { it.copy(isSaving = false) }
            }
        }
    }

    /**
     * 프로필 이미지 변경 클릭 - Photo Picker 실행 요청
     */
    fun onProfileImageClicked() {
        viewModelScope.launch {
            _mypageEvent.emit(MypageEvent.LaunchPhotoPicker)
        }
    }

    /**
     * 프로필 이미지 업데이트 공통 로직
     */
    private fun updateProfileImage(uri: Uri?) {
        viewModelScope.launch {
            val userId = getUserId() ?: return@launch

            _uiState.value = MyUiState.Loading

            updateUserProfileImageUseCase(userId = userId, imageUri = uri)
                .onSuccess { userInfo ->
                    _uiState.value = MyUiState.Success(userInfo)
                }
                .onFailure { exception ->
                    handleError(exception)
                    _uiState.value = MyUiState.Error
                }
        }
    }

    /**
     * Photo Picker에서 선택된 Uri 업데이트
     */
    fun onImageSelected(uri: Uri?) {
        if (uri == null) return
        updateProfileImage(uri)
    }

    /**
     * 기본 프로필 이미지로 변경
     */
    fun onResetToDefaultImage() {
        updateProfileImage(null)
    }

    private fun getUserId(): Int? {
        return (uiState.value as? MyUiState.Success)?.userInfo?.userId
    }

    /**
     * 로그아웃
     */
    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
                .onSuccess {
                    Timber.d("로그아웃 성공")
                }
                .onFailure { exception ->
                    handleError(exception)
                }
        }
    }

    /**
     * 회원탈퇴
     */
    fun unlinkUser(onSuccess: () -> Unit, onFailure: () -> Unit) {
        val currentState = _uiState.value as? MyUiState.Success ?: return

        viewModelScope.launch {
            _isUnlinking.value = true
            unlinkUseCase(currentState.userInfo.loginType)
                .onSuccess {
                    _isUnlinking.value = false
                    onSuccess()
                }
                .onFailure { exception ->
                    handleError(exception)
                    _isUnlinking.value = false
                    onFailure()
                }
        }
    }
}
