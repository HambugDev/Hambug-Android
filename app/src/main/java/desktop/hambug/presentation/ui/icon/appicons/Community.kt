package desktop.hambug.presentation.ui.icon.appicons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.icon.AppIcons
import kotlin.Unit

public val AppIcons.Community: ImageVector
    get() {
        if (_community != null) {
            return _community!!
        }
        _community = Builder(name = "Community", defaultWidth = 23.0.dp, defaultHeight = 22.0.dp,
                viewportWidth = 23.0f, viewportHeight = 22.0f).apply {
            path(fill = SolidColor(Color(0xFFA8A29E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.406f, 11.37f)
                verticalLineTo(5.573f)
                horizontalLineTo(2.267f)
                curveTo(1.7f, 5.573f, 1.156f, 5.802f, 0.755f, 6.209f)
                curveTo(0.354f, 6.617f, 0.128f, 7.17f, 0.128f, 7.746f)
                verticalLineTo(20.79f)
                curveTo(0.129f, 20.925f, 0.167f, 21.056f, 0.238f, 21.17f)
                curveTo(0.308f, 21.284f, 0.409f, 21.376f, 0.528f, 21.435f)
                curveTo(0.642f, 21.488f, 0.768f, 21.509f, 0.894f, 21.496f)
                curveTo(1.019f, 21.484f, 1.139f, 21.438f, 1.241f, 21.362f)
                lineTo(4.912f, 18.616f)
                horizontalLineTo(14.494f)
                curveTo(14.765f, 18.624f, 15.033f, 18.576f, 15.285f, 18.474f)
                curveTo(15.536f, 18.372f, 15.764f, 18.219f, 15.954f, 18.024f)
                curveTo(16.145f, 17.829f, 16.294f, 17.597f, 16.392f, 17.341f)
                curveTo(16.49f, 17.085f, 16.536f, 16.811f, 16.526f, 16.536f)
                verticalLineTo(15.717f)
                horizontalLineTo(8.684f)
                curveTo(7.549f, 15.717f, 6.461f, 15.259f, 5.659f, 14.444f)
                curveTo(4.857f, 13.629f, 4.406f, 12.523f, 4.406f, 11.37f)
                close()
            }
            path(fill = SolidColor(Color(0xFFA8A29E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(20.804f, 0.5f)
                horizontalLineTo(8.684f)
                curveTo(8.117f, 0.5f, 7.573f, 0.729f, 7.171f, 1.137f)
                curveTo(6.77f, 1.544f, 6.545f, 2.097f, 6.545f, 2.674f)
                verticalLineTo(11.37f)
                curveTo(6.545f, 11.946f, 6.77f, 12.499f, 7.171f, 12.907f)
                curveTo(7.573f, 13.315f, 8.117f, 13.544f, 8.684f, 13.544f)
                horizontalLineTo(18.344f)
                lineTo(21.752f, 16.232f)
                curveTo(21.854f, 16.308f, 21.973f, 16.356f, 22.098f, 16.37f)
                curveTo(22.223f, 16.384f, 22.35f, 16.364f, 22.465f, 16.312f)
                curveTo(22.587f, 16.253f, 22.689f, 16.161f, 22.761f, 16.045f)
                curveTo(22.833f, 15.93f, 22.871f, 15.796f, 22.872f, 15.66f)
                verticalLineTo(2.674f)
                curveTo(22.872f, 2.11f, 22.656f, 1.567f, 22.271f, 1.162f)
                curveTo(21.885f, 0.756f, 21.359f, 0.519f, 20.804f, 0.5f)
                close()
            }
        }
        .build()
        return _community!!
    }

private var _community: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Community, contentDescription = "")
    }
}
