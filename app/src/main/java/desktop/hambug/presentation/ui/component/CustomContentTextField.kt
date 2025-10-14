package desktop.hambug.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun CustomContentTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(color = HambugTheme.colors.bgLighter, shape = RoundedCornerShape(6.dp))
            .border(width = 1.dp, color = HambugTheme.colors.borderDefault, shape = RoundedCornerShape(6.dp))
            .padding(12.dp),
        value = value,
        onValueChange = onValueChange,
        textStyle = HambugTheme.typography.body03,
        singleLine = false
    )
}
