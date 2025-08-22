package desktop.hambug.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class HambugColors(
    // primary palette
    val primRed: Color,
    val primGray: Color,
    val primBackground: Color,

    // secondary palette
    val secondBrown: Color,
    val secondYellow: Color,
    val secondRed: Color,
    val secondGreen: Color,

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
    val bgLight: Color,
    val bgLighter: Color,
    val bgWhite: Color,

    val isDark: Boolean
)

val LightColorPalette = HambugColors(
    primRed = HambugRed,
    primGray = HambugGray,
    primBackground = HambugBackground,
    secondBrown = Brown400,
    secondYellow = Yellow400,
    secondRed = Red400,
    secondGreen = Green400,
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
    bgLight = Gray75,
    bgLighter = Gray50,
    bgWhite = Gray0,
    isDark = false
)

val DarkColorPalette = LightColorPalette.copy(
    isDark = true
)

// 커스텀 색상 CompositionLocal 정의
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
    error = colors.secondRed,
    onError = colors.bgWhite,
)

@Composable
fun HambugTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(
        LocalHambugColors provides colors
    ) {
        MaterialTheme(
            colorScheme = hambugLightColorScheme(colors),
            content = content
        )
    }
}

// UI에서 커스텀 색상 사용 가능하게 확장속성 정의
object HambugTheme {
    val colors: HambugColors
        @Composable
        get() = LocalHambugColors.current
}
