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

public val AppIcons.Bell: ImageVector
    get() {
        if (_bell != null) {
            return _bell!!
        }
        _bell = Builder(name = "Bell", defaultWidth = 19.0.dp, defaultHeight = 21.0.dp,
                viewportWidth = 19.0f, viewportHeight = 21.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(9.5f, 0.0f)
                curveTo(7.42f, 0.0f, 5.426f, 0.83f, 3.955f, 2.307f)
                curveTo(2.484f, 3.783f, 1.658f, 5.786f, 1.658f, 7.875f)
                verticalLineTo(12.582f)
                lineTo(0.734f, 13.51f)
                curveTo(0.551f, 13.693f, 0.427f, 13.927f, 0.377f, 14.182f)
                curveTo(0.326f, 14.436f, 0.352f, 14.7f, 0.451f, 14.94f)
                curveTo(0.55f, 15.179f, 0.717f, 15.384f, 0.932f, 15.529f)
                curveTo(1.147f, 15.673f, 1.4f, 15.75f, 1.658f, 15.75f)
                horizontalLineTo(17.342f)
                curveTo(17.6f, 15.75f, 17.853f, 15.673f, 18.068f, 15.529f)
                curveTo(18.283f, 15.384f, 18.45f, 15.179f, 18.549f, 14.94f)
                curveTo(18.648f, 14.7f, 18.674f, 14.436f, 18.623f, 14.182f)
                curveTo(18.573f, 13.927f, 18.449f, 13.693f, 18.266f, 13.51f)
                lineTo(17.342f, 12.582f)
                verticalLineTo(7.875f)
                curveTo(17.342f, 5.786f, 16.516f, 3.783f, 15.045f, 2.307f)
                curveTo(13.574f, 0.83f, 11.58f, 0.0f, 9.5f, 0.0f)
                close()
                moveTo(9.5f, 21.0f)
                curveTo(8.46f, 21.0f, 7.463f, 20.585f, 6.728f, 19.847f)
                curveTo(5.992f, 19.108f, 5.579f, 18.107f, 5.579f, 17.063f)
                horizontalLineTo(13.421f)
                curveTo(13.421f, 18.107f, 13.008f, 19.108f, 12.273f, 19.847f)
                curveTo(11.537f, 20.585f, 10.54f, 21.0f, 9.5f, 21.0f)
                close()
            }
        }
        .build()
        return _bell!!
    }

private var _bell: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Bell, contentDescription = "")
    }
}
