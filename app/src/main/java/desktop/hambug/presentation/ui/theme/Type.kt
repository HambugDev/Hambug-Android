package desktop.hambug.presentation.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import desktop.hambug.R

private val pretendard = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

/**
 * 커스텀 폰트 스타일 정의
 */
@Immutable
data class AppTypography(
    val heading01: TextStyle,
    val heading02: TextStyle,
    val heading03: TextStyle,
    val heading04: TextStyle,
    val heading05: TextStyle,
    val title01: TextStyle,
    val title02: TextStyle,
    val title03: TextStyle,
    val title04: TextStyle,
    val body01: TextStyle,
    val body01Prominent: TextStyle,
    val body02: TextStyle,
    val body02Reading: TextStyle,
    val body02Prominent: TextStyle,
    val body03: TextStyle,
    val body03Prominent: TextStyle,
    val body04: TextStyle,
    val body04Prominent: TextStyle,
    val label01: TextStyle,
    val label02: TextStyle,
    val label03: TextStyle
)

/**
 * CompositionLocal을 통해 커스텀 typography 제공
 */
val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("No AppTypography provided")
}

fun getAppTypography(): AppTypography {
    return AppTypography(
        // heading
        heading01 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = (28 * 1.25).sp,  // 125%
            letterSpacing = (-0.014).em  // -1.4%
        ),
        heading02 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = (24 * 1.3).sp,
            letterSpacing = (-0.014).em
        ),
        heading03 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = (22 * 1.3).sp,
            letterSpacing = (-0.014).em
        ),
        heading04 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = (20 * 1.35).sp,
            letterSpacing = (-0.014).em
        ),
        heading05 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = (18 * 1.3).sp,
            letterSpacing = (-0.014).em
        ),

        // title
        title01 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = (20 * 1.4).sp,
            letterSpacing = (-0.014).em
        ),
        title02 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            lineHeight = (17 * 1.4).sp,
            letterSpacing = (-0.02).em
        ),
        title03 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            lineHeight = (15 * 1.4).sp,
            letterSpacing = (-0.03).em
        ),
        title04 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = (14 * 1.4).sp,
            letterSpacing = (-0.03).em
        ),

        // body
        body01 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = (17 * 1.55).sp,
            letterSpacing = (-0.03).em
        ),
        body01Prominent = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            lineHeight = (17 * 1.55).sp,
            letterSpacing = (-0.03).em
        ),
        body02 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = (16 * 1.55).sp,
            letterSpacing = (-0.024).em
        ),
        body02Reading = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = (15 * 1.55).sp,
            letterSpacing = (-0.03).em
        ),
        body02Prominent = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            lineHeight = (15 * 1.55).sp,
            letterSpacing = (-0.03).em
        ),
        body03 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = (14 * 1.5).sp,
            letterSpacing = (-0.02).em
        ),
        body03Prominent = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = (14 * 1.5).sp,
            letterSpacing = (-0.02).em
        ),
        body04 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = (12 * 1.4).sp,
        ),
        body04Prominent = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = (12 * 1.4).sp,
        ),

        // label
        label01 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = (14 * 1.4).sp,
            letterSpacing = (-0.03).em
        ),
        label02 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = (12 * 1.4).sp,
            letterSpacing = (-0.03).em
        ),
        label03 = TextStyle(
            fontFamily = pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = (12 * 1.4).sp,
            letterSpacing = (-0.03).em
        )
    )
}
