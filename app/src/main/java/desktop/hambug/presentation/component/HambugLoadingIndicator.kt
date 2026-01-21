package desktop.hambug.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.theme.HambugTheme

enum class IndicatorSize(val size: Dp, val strokeWidth: Dp) {
    Small(24.dp, 2.dp),
    Big(40.dp, 4.dp)
}

@Composable
fun HambugLoadingIndicator(
    modifier: Modifier = Modifier,
    indicatorSize: IndicatorSize = IndicatorSize.Big
) {
    CircularProgressIndicator(
        modifier = modifier.size(indicatorSize.size),
        color = HambugTheme.colors.primRed,
        strokeWidth = indicatorSize.strokeWidth
    )
}
