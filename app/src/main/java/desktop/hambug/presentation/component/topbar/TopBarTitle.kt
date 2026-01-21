package desktop.hambug.presentation.component.topbar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun TopBarTitle(
    text: String,
    color: Color = HambugTheme.colors.textHeadline
) {
    Text(
        text = text,
        style = HambugTheme.typography.title02,
        color = color
    )
}
