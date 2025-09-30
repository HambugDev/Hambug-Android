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

public val AppIcons.CircleCross: ImageVector
    get() {
        if (_circleCross != null) {
            return _circleCross!!
        }
        _circleCross = Builder(name = "CircleCross", defaultWidth = 20.0.dp, defaultHeight =
                20.0.dp, viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(10.0f, 0.0f)
                curveTo(4.477f, 0.0f, 0.0f, 4.477f, 0.0f, 10.0f)
                curveTo(0.0f, 15.523f, 4.477f, 20.0f, 10.0f, 20.0f)
                curveTo(15.523f, 20.0f, 20.0f, 15.523f, 20.0f, 10.0f)
                curveTo(19.994f, 4.48f, 15.52f, 0.006f, 10.0f, 0.0f)
                close()
                moveTo(13.333f, 12.156f)
                curveTo(13.672f, 12.467f, 13.694f, 12.995f, 13.382f, 13.333f)
                curveTo(13.071f, 13.672f, 12.543f, 13.694f, 12.205f, 13.382f)
                curveTo(12.188f, 13.367f, 12.171f, 13.35f, 12.156f, 13.333f)
                lineTo(10.0f, 11.178f)
                lineTo(7.845f, 13.333f)
                curveTo(7.514f, 13.653f, 6.986f, 13.644f, 6.667f, 13.313f)
                curveTo(6.355f, 12.99f, 6.355f, 12.478f, 6.667f, 12.155f)
                lineTo(8.822f, 10.0f)
                lineTo(6.667f, 7.845f)
                curveTo(6.347f, 7.514f, 6.356f, 6.986f, 6.687f, 6.667f)
                curveTo(7.01f, 6.355f, 7.522f, 6.355f, 7.845f, 6.667f)
                lineTo(10.0f, 8.822f)
                lineTo(12.156f, 6.667f)
                curveTo(12.467f, 6.328f, 12.995f, 6.306f, 13.333f, 6.618f)
                curveTo(13.672f, 6.93f, 13.694f, 7.457f, 13.382f, 7.795f)
                curveTo(13.367f, 7.812f, 13.35f, 7.829f, 13.333f, 7.844f)
                lineTo(11.178f, 10.0f)
                lineTo(13.333f, 12.156f)
                close()
            }
        }
        .build()
        return _circleCross!!
    }

private var _circleCross: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.CircleCross, contentDescription = "")
    }
}
