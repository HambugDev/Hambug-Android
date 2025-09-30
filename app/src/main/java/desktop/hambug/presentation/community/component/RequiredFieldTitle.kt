package desktop.hambug.presentation.community.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun RequiredFieldTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(style = HambugTheme.typography.title02.toSpanStyle().copy(
                HambugTheme.colors.textBody
            )) {
                append(title)
            }
            withStyle(style = HambugTheme.typography.title02.toSpanStyle().copy(
                color = HambugTheme.colors.primRed
            )) {
                append("*")
            }
        }
    )
}
