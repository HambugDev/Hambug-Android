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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.icon.AppIcons
import kotlin.Unit

public val AppIcons.Activity: ImageVector
    get() {
        if (_activity != null) {
            return _activity!!
        }
        _activity = Builder(name = "Activity", defaultWidth = 16.0.dp, defaultHeight = 16.0.dp,
                viewportWidth = 16.0f, viewportHeight = 16.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(4.667f, 0.0f)
                    horizontalLineTo(2.667f)
                    curveTo(1.194f, 0.0f, 0.0f, 1.194f, 0.0f, 2.667f)
                    verticalLineTo(4.667f)
                    curveTo(0.0f, 6.139f, 1.194f, 7.333f, 2.667f, 7.333f)
                    horizontalLineTo(4.667f)
                    curveTo(6.139f, 7.333f, 7.333f, 6.139f, 7.333f, 4.667f)
                    verticalLineTo(2.667f)
                    curveTo(7.333f, 1.194f, 6.139f, 0.0f, 4.667f, 0.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(13.333f, 0.0f)
                    horizontalLineTo(11.333f)
                    curveTo(9.86f, 0.0f, 8.667f, 1.194f, 8.667f, 2.667f)
                    verticalLineTo(4.667f)
                    curveTo(8.667f, 6.139f, 9.86f, 7.333f, 11.333f, 7.333f)
                    horizontalLineTo(13.333f)
                    curveTo(14.806f, 7.333f, 16.0f, 6.139f, 16.0f, 4.667f)
                    verticalLineTo(2.667f)
                    curveTo(16.0f, 1.194f, 14.806f, 0.0f, 13.333f, 0.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(4.667f, 8.667f)
                    horizontalLineTo(2.667f)
                    curveTo(1.194f, 8.667f, 0.0f, 9.861f, 0.0f, 11.333f)
                    verticalLineTo(13.333f)
                    curveTo(0.0f, 14.806f, 1.194f, 16.0f, 2.667f, 16.0f)
                    horizontalLineTo(4.667f)
                    curveTo(6.139f, 16.0f, 7.333f, 14.806f, 7.333f, 13.333f)
                    verticalLineTo(11.333f)
                    curveTo(7.333f, 9.861f, 6.139f, 8.667f, 4.667f, 8.667f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(13.333f, 8.667f)
                    horizontalLineTo(11.333f)
                    curveTo(9.86f, 8.667f, 8.667f, 9.861f, 8.667f, 11.333f)
                    verticalLineTo(13.333f)
                    curveTo(8.667f, 14.806f, 9.86f, 16.0f, 11.333f, 16.0f)
                    horizontalLineTo(13.333f)
                    curveTo(14.806f, 16.0f, 16.0f, 14.806f, 16.0f, 13.333f)
                    verticalLineTo(11.333f)
                    curveTo(16.0f, 9.861f, 14.806f, 8.667f, 13.333f, 8.667f)
                    close()
                }
            }
        }
        .build()
        return _activity!!
    }

private var _activity: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Activity, contentDescription = "")
    }
}
