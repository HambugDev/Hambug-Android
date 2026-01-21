package desktop.hambug.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun CustomTitleTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = HambugTheme.typography.body02Prominent,
        singleLine = true
    )
}
