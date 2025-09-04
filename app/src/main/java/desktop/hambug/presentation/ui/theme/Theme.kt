package desktop.hambug.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class HambugColors(
    // primary palette
    val primRed: Color,
    val primGray: Color,
    val primWhite: Color,

    // secondary palette
    val secondOrange: Color,
    val secondYellow: Color,
    val secondGreen: Color,
    val secondBrown: Color,

    // text color
    val textHeadline: Color,
    val textBody: Color,
    val textDisabled: Color,

    // border color
    val borderHoverFocus: Color,
    val borderDefault: Color,
    val borderDisabled: Color,

    // icon color
    val iconDefault: Color,
    val iconDisabled: Color,

    // background color
    val bgDarker: Color,
    val bgNormal: Color,
    val bgLighter: Color,
    val bgWhite: Color,
    val bgYellow: Color,

    val isDark: Boolean
)

val LightColorPalette = HambugColors(
    primRed = HambugRed,
    primGray = HambugGray,
    primWhite = HambugWhite,
    secondOrange = Orange400,
    secondYellow = Yellow400,
    secondGreen = Green400,
    secondBrown = Brown400,
    textHeadline = Gray900,
    textBody = Gray800,
    textDisabled = Gray600,
    borderHoverFocus = Gray500,
    borderDefault = Gray400,
    borderDisabled = Gray300,
    iconDefault = IconGray800,
    iconDisabled = IconGray600,
    bgDarker = Gray200,
    bgNormal = Gray100,
    bgLighter = Gray50,
    bgWhite = Gray0,
    bgYellow = BgYellow,
    isDark = false
)

val DarkColorPalette = LightColorPalette.copy(
    isDark = true
)

/**
 * CompositionLocal을 통해 커스텀 색상 팔레트 제공
 */
private val LocalHambugColors = staticCompositionLocalOf<HambugColors> {
    error("No HambugColorPalette provided")
}

// Material3 기본 ColorScheme에 커스텀 색상 매핑
private fun hambugLightColorScheme(colors: HambugColors) = lightColorScheme(
    primary = colors.primRed,
    onPrimary = colors.bgWhite,
    background = colors.bgNormal,
    onBackground = colors.textBody,
    surface = colors.bgWhite,
    onSurface = colors.textBody,
    error = colors.primRed,
    onError = colors.bgWhite,
)

@Composable
fun HambugTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val appTypography = getAppTypography()

    CompositionLocalProvider(
        LocalHambugColors provides colors,
        LocalSpacing provides Spacing(),
        LocalAppTypography provides appTypography
    ) {
        MaterialTheme(
            colorScheme = hambugLightColorScheme(colors),
            content = content
        )
    }
}

/**
 * Hambug 디자인 속성에 접근하기 위한 객체
 */
object HambugTheme {
    val colors: HambugColors
        @Composable
        @ReadOnlyComposable
        get() = LocalHambugColors.current

    val spacing: Spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
}
