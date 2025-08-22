package desktop.hambug.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
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

// Spacing CompositionLocal 정의 (spacing 값 제공)
val LocalSpacing = staticCompositionLocalOf { Spacing() }

// UI에서 MaterialTheme.spacing으로 접근 가능하게 확장속성 정의
val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
