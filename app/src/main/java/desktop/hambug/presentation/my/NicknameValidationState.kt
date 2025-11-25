package desktop.hambug.presentation.my

data class NicknameValidationState(
    val currentNickname: String = "",
    val errorMessage: String? = null,
    val isValid: Boolean = false,
    val isSaving: Boolean = false
)
