package desktop.hambug.presentation.ui.icon.appicons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.icon.AppIcons
import kotlin.Unit

public val AppIcons.Home: ImageVector
    get() {
        if (_home != null) {
            return _home!!
        }
        _home = Builder(name = "Home", defaultWidth = 22.0.dp, defaultHeight = 22.0.dp,
                viewportWidth = 22.0f, viewportHeight = 22.0f).apply {
            path(fill = SolidColor(Color(0xFFEC6D55)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.5f, 20.135f)
                verticalLineTo(8.271f)
                curveTo(0.5f, 7.921f, 0.586f, 7.591f, 0.758f, 7.278f)
                curveTo(0.93f, 6.966f, 1.167f, 6.709f, 1.469f, 6.507f)
                lineTo(9.547f, 0.941f)
                curveTo(9.969f, 0.647f, 10.453f, 0.5f, 10.995f, 0.5f)
                curveTo(11.538f, 0.5f, 12.024f, 0.647f, 12.453f, 0.941f)
                lineTo(20.531f, 6.506f)
                curveTo(20.834f, 6.708f, 21.071f, 6.965f, 21.242f, 7.278f)
                curveTo(21.414f, 7.591f, 21.5f, 7.921f, 21.5f, 8.271f)
                verticalLineTo(20.135f)
                curveTo(21.5f, 20.501f, 21.351f, 20.82f, 21.052f, 21.092f)
                curveTo(20.753f, 21.364f, 20.402f, 21.5f, 20.0f, 21.5f)
                horizontalLineTo(14.924f)
                curveTo(14.58f, 21.5f, 14.292f, 21.394f, 14.06f, 21.183f)
                curveTo(13.828f, 20.971f, 13.712f, 20.709f, 13.712f, 20.397f)
                verticalLineTo(13.888f)
                curveTo(13.712f, 13.575f, 13.596f, 13.314f, 13.364f, 13.103f)
                curveTo(13.131f, 12.891f, 12.843f, 12.785f, 12.5f, 12.785f)
                horizontalLineTo(9.5f)
                curveTo(9.157f, 12.785f, 8.87f, 12.891f, 8.637f, 13.103f)
                curveTo(8.405f, 13.314f, 8.288f, 13.575f, 8.288f, 13.888f)
                verticalLineTo(20.399f)
                curveTo(8.288f, 20.711f, 8.172f, 20.972f, 7.94f, 21.183f)
                curveTo(7.708f, 21.394f, 7.42f, 21.5f, 7.077f, 21.5f)
                horizontalLineTo(2.0f)
                curveTo(1.598f, 21.5f, 1.247f, 21.364f, 0.948f, 21.092f)
                curveTo(0.65f, 20.82f, 0.5f, 20.501f, 0.5f, 20.135f)
                close()
            }
        }
        .build()
        return _home!!
    }

private var _home: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Home, contentDescription = "")
    }
}
