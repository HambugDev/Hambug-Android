package desktop.hambug.presentation.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val none: Dp = 0.dp,
    val xsmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val xlarge: Dp = 20.dp,
    val xxlarge: Dp = 24.dp,
    val extra: Dp = 32.dp,
    val xextra: Dp = 48.dp,
    val xxextra: Dp = 64.dp
)

/**
 * CompositionLocal을 통해 spacing 값 제공
 */
val LocalSpacing = staticCompositionLocalOf<Spacing> {
    error("No spacing provided")
}
