package desktop.hambug.presentation.community

import android.net.Uri

data class BoardWriteUiState(
    val selectedImageUris: List<Uri> = emptyList(),
    val isCreating: Boolean = false
)
