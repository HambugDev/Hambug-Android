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

public val AppIcons.Heart: ImageVector
    get() {
        if (_heart != null) {
            return _heart!!
        }
        _heart = Builder(name = "Heart", defaultWidth = 12.0.dp, defaultHeight = 12.0.dp,
                viewportWidth = 12.0f, viewportHeight = 12.0f).apply {
            path(fill = SolidColor(Color(0xFFF65555)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(8.75f, 0.958f)
                curveTo(8.187f, 0.967f, 7.636f, 1.124f, 7.153f, 1.414f)
                curveTo(6.671f, 1.704f, 6.273f, 2.116f, 6.0f, 2.608f)
                curveTo(5.727f, 2.116f, 5.329f, 1.704f, 4.847f, 1.414f)
                curveTo(4.364f, 1.124f, 3.813f, 0.967f, 3.25f, 0.958f)
                curveTo(2.352f, 0.997f, 1.507f, 1.39f, 0.898f, 2.05f)
                curveTo(0.289f, 2.711f, -0.034f, 3.586f, -0.0f, 4.483f)
                curveTo(-0.0f, 6.757f, 2.393f, 9.24f, 4.4f, 10.923f)
                curveTo(4.848f, 11.3f, 5.415f, 11.506f, 6.0f, 11.506f)
                curveTo(6.585f, 11.506f, 7.152f, 11.3f, 7.6f, 10.923f)
                curveTo(9.607f, 9.24f, 12.0f, 6.757f, 12.0f, 4.483f)
                curveTo(12.034f, 3.586f, 11.711f, 2.711f, 11.102f, 2.05f)
                curveTo(10.493f, 1.39f, 9.648f, 0.997f, 8.75f, 0.958f)
                close()
            }
        }
        .build()
        return _heart!!
    }

private var _heart: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Heart, contentDescription = "")
    }
}
