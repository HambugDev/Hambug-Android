package desktop.hambug.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun CustomContentTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier.fillMaxSize(),
        value = value,
        onValueChange = onValueChange,
        textStyle = HambugTheme.typography.body03,
        singleLine = false
    )
}
