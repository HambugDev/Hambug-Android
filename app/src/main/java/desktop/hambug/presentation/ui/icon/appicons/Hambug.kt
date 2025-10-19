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

public val AppIcons.Hambug: ImageVector
    get() {
        if (_hambug != null) {
            return _hambug!!
        }
        _hambug = Builder(name = "Hambug", defaultWidth = 29.0.dp, defaultHeight = 25.0.dp,
                viewportWidth = 29.0f, viewportHeight = 25.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(1.628f, 10.978f)
                horizontalLineTo(26.406f)
                curveTo(27.305f, 10.978f, 28.034f, 11.705f, 28.034f, 12.602f)
                verticalLineTo(14.582f)
                curveTo(28.034f, 15.479f, 27.305f, 16.206f, 26.406f, 16.206f)
                horizontalLineTo(1.628f)
                curveTo(0.729f, 16.206f, 0.0f, 15.479f, 0.0f, 14.582f)
                verticalLineTo(12.602f)
                curveTo(0.0f, 11.705f, 0.729f, 10.978f, 1.628f, 10.978f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(10.354f, 0.0f)
                horizontalLineTo(17.73f)
                curveTo(22.105f, 0.0f, 25.616f, 3.509f, 25.616f, 7.883f)
                curveTo(25.616f, 9.206f, 24.547f, 10.325f, 23.174f, 10.325f)
                horizontalLineTo(4.86f)
                curveTo(3.537f, 10.325f, 2.418f, 9.257f, 2.418f, 7.883f)
                curveTo(2.418f, 3.509f, 5.928f, 0.0f, 10.303f, 0.0f)
                horizontalLineTo(10.354f)
                close()
                moveTo(18.087f, 7.68f)
                curveTo(18.087f, 7.985f, 18.341f, 8.239f, 18.646f, 8.239f)
                curveTo(18.951f, 8.239f, 19.206f, 7.985f, 19.206f, 7.68f)
                verticalLineTo(6.968f)
                curveTo(19.206f, 6.663f, 18.951f, 6.408f, 18.646f, 6.408f)
                curveTo(18.341f, 6.408f, 18.087f, 6.663f, 18.087f, 6.968f)
                verticalLineTo(7.68f)
                close()
                moveTo(15.696f, 7.68f)
                curveTo(15.696f, 7.985f, 15.95f, 8.239f, 16.255f, 8.239f)
                curveTo(16.56f, 8.239f, 16.815f, 7.985f, 16.815f, 7.68f)
                verticalLineTo(6.968f)
                curveTo(16.815f, 6.663f, 16.56f, 6.408f, 16.255f, 6.408f)
                curveTo(15.95f, 6.408f, 15.696f, 6.663f, 15.696f, 6.968f)
                verticalLineTo(7.68f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(23.924f, 16.859f)
                horizontalLineTo(4.045f)
                curveTo(3.13f, 16.859f, 2.418f, 17.574f, 2.418f, 18.493f)
                curveTo(2.418f, 21.864f, 5.112f, 24.57f, 8.468f, 24.57f)
                horizontalLineTo(19.5f)
                curveTo(22.856f, 24.57f, 25.551f, 21.864f, 25.551f, 18.493f)
                curveTo(25.551f, 17.574f, 24.839f, 16.859f, 23.924f, 16.859f)
                close()
            }
        }
        .build()
        return _hambug!!
    }

private var _hambug: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Hambug, contentDescription = "")
    }
}
