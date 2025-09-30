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

public val AppIcons.BackDetail: ImageVector
    get() {
        if (_backDetail != null) {
            return _backDetail!!
        }
        _backDetail = Builder(name = "BackDetail", defaultWidth = 8.0.dp, defaultHeight = 14.0.dp,
                viewportWidth = 8.0f, viewportHeight = 14.0f).apply {
            path(fill = SolidColor(Color(0xFF292524)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(2.6f, 7.71f)
                curveTo(2.506f, 7.617f, 2.432f, 7.506f, 2.381f, 7.385f)
                curveTo(2.33f, 7.263f, 2.304f, 7.132f, 2.304f, 7.0f)
                curveTo(2.304f, 6.868f, 2.33f, 6.737f, 2.381f, 6.615f)
                curveTo(2.432f, 6.494f, 2.506f, 6.383f, 2.6f, 6.29f)
                lineTo(7.19f, 1.71f)
                curveTo(7.284f, 1.617f, 7.358f, 1.507f, 7.409f, 1.385f)
                curveTo(7.46f, 1.263f, 7.486f, 1.132f, 7.486f, 1.0f)
                curveTo(7.486f, 0.868f, 7.46f, 0.737f, 7.409f, 0.615f)
                curveTo(7.358f, 0.494f, 7.284f, 0.383f, 7.19f, 0.29f)
                curveTo(7.003f, 0.104f, 6.749f, -0.001f, 6.485f, -0.001f)
                curveTo(6.221f, -0.001f, 5.967f, 0.104f, 5.78f, 0.29f)
                lineTo(1.19f, 4.88f)
                curveTo(0.628f, 5.443f, 0.313f, 6.205f, 0.313f, 7.0f)
                curveTo(0.313f, 7.795f, 0.628f, 8.558f, 1.19f, 9.12f)
                lineTo(5.78f, 13.71f)
                curveTo(5.966f, 13.895f, 6.218f, 13.999f, 6.48f, 14.0f)
                curveTo(6.612f, 14.001f, 6.742f, 13.976f, 6.864f, 13.926f)
                curveTo(6.986f, 13.876f, 7.096f, 13.803f, 7.19f, 13.71f)
                curveTo(7.284f, 13.617f, 7.358f, 13.507f, 7.409f, 13.385f)
                curveTo(7.46f, 13.263f, 7.486f, 13.132f, 7.486f, 13.0f)
                curveTo(7.486f, 12.868f, 7.46f, 12.737f, 7.409f, 12.616f)
                curveTo(7.358f, 12.494f, 7.284f, 12.383f, 7.19f, 12.29f)
                lineTo(2.6f, 7.71f)
                close()
            }
        }
        .build()
        return _backDetail!!
    }

private var _backDetail: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.BackDetail, contentDescription = "")
    }
}
