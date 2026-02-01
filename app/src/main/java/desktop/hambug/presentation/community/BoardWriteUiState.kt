package desktop.hambug.presentation.community

import android.net.Uri

sealed class BoardWriteUiState {
    data object Loading : BoardWriteUiState()
    data class Success(
        val existingImageUrls: List<String> = emptyList(),  // 서버에서 받아온 URL
        val selectedImageUris: List<Uri> = emptyList(),     // 유저가 선택한 이미지
        val isSaving: Boolean = false
    ) : BoardWriteUiState()
    data object Error : BoardWriteUiState()
}
