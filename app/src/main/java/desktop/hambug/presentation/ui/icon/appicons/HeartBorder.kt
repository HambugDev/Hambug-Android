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

public val AppIcons.HeartBorder: ImageVector
    get() {
        if (_heartBorder != null) {
            return _heartBorder!!
        }
        _heartBorder = Builder(name = "HeartBorder", defaultWidth = 26.0.dp, defaultHeight =
                23.0.dp, viewportWidth = 26.0f, viewportHeight = 23.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFF65555)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(1.005f, 7.648f)
                curveTo(0.941f, 5.953f, 1.547f, 4.302f, 2.688f, 3.057f)
                curveTo(3.82f, 1.82f, 5.389f, 1.083f, 7.055f, 1.001f)
                curveTo(8.088f, 1.022f, 9.098f, 1.314f, 9.985f, 1.85f)
                curveTo(10.88f, 2.39f, 11.618f, 3.159f, 12.124f, 4.079f)
                lineTo(13.0f, 5.674f)
                lineTo(13.876f, 4.079f)
                curveTo(14.382f, 3.159f, 15.12f, 2.39f, 16.015f, 1.85f)
                curveTo(16.901f, 1.314f, 17.912f, 1.022f, 18.944f, 1.001f)
                curveTo(20.61f, 1.082f, 22.179f, 1.819f, 23.313f, 3.057f)
                curveTo(24.453f, 4.302f, 25.059f, 5.953f, 24.995f, 7.648f)
                verticalLineTo(7.687f)
                curveTo(24.995f, 9.838f, 23.856f, 12.215f, 22.063f, 14.587f)
                curveTo(20.286f, 16.936f, 17.966f, 19.153f, 15.82f, 20.965f)
                horizontalLineTo(15.819f)
                curveTo(15.029f, 21.634f, 14.031f, 22.0f, 13.0f, 22.0f)
                curveTo(11.969f, 22.0f, 10.971f, 21.634f, 10.181f, 20.965f)
                horizontalLineTo(10.18f)
                curveTo(8.034f, 19.153f, 5.714f, 16.936f, 3.938f, 14.587f)
                curveTo(2.144f, 12.215f, 1.005f, 9.838f, 1.005f, 7.687f)
                verticalLineTo(7.648f)
                close()
            }
        }
        .build()
        return _heartBorder!!
    }

private var _heartBorder: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.HeartBorder, contentDescription = "")
    }
}
