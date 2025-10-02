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

public val AppIcons.Pen: ImageVector
    get() {
        if (_pen != null) {
            return _pen!!
        }
        _pen = Builder(name = "Pen", defaultWidth = 16.0.dp, defaultHeight = 16.0.dp, viewportWidth
                = 16.0f, viewportHeight = 16.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(0.781f, 12.746f)
                    curveTo(0.281f, 13.246f, 0.0f, 13.924f, 0.0f, 14.631f)
                    lineTo(0.0f, 16.0f)
                    horizontalLineTo(1.369f)
                    curveTo(2.076f, 16.0f, 2.754f, 15.719f, 3.254f, 15.218f)
                    lineTo(12.149f, 6.323f)
                    lineTo(9.677f, 3.85f)
                    lineTo(0.781f, 12.746f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(15.43f, 0.57f)
                    curveTo(15.267f, 0.407f, 15.075f, 0.279f, 14.863f, 0.191f)
                    curveTo(14.65f, 0.103f, 14.423f, 0.057f, 14.193f, 0.057f)
                    curveTo(13.964f, 0.057f, 13.736f, 0.103f, 13.524f, 0.191f)
                    curveTo(13.312f, 0.279f, 13.119f, 0.407f, 12.957f, 0.57f)
                    lineTo(10.619f, 2.908f)
                    lineTo(13.092f, 5.381f)
                    lineTo(15.43f, 3.043f)
                    curveTo(15.592f, 2.881f, 15.721f, 2.688f, 15.809f, 2.476f)
                    curveTo(15.897f, 2.264f, 15.943f, 2.036f, 15.943f, 1.807f)
                    curveTo(15.943f, 1.577f, 15.897f, 1.35f, 15.809f, 1.137f)
                    curveTo(15.721f, 0.925f, 15.592f, 0.732f, 15.43f, 0.57f)
                    close()
                }
            }
        }
        .build()
        return _pen!!
    }

private var _pen: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Pen, contentDescription = "")
    }
}
