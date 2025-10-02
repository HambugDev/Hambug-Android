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

public val AppIcons.CommentBorder: ImageVector
    get() {
        if (_commentBorder != null) {
            return _commentBorder!!
        }
        _commentBorder = Builder(name = "CommentBorder", defaultWidth = 12.0.dp, defaultHeight =
                12.0.dp, viewportWidth = 12.0f, viewportHeight = 12.0f).apply {
            path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.0f, 8.0f)
                verticalLineTo(10.5f)
                curveTo(12.0f, 10.898f, 11.842f, 11.279f, 11.561f, 11.561f)
                curveTo(11.279f, 11.842f, 10.898f, 12.0f, 10.5f, 12.0f)
                horizontalLineTo(8.0f)
                curveTo(7.299f, 11.999f, 6.61f, 11.814f, 6.002f, 11.463f)
                curveTo(5.395f, 11.112f, 4.891f, 10.608f, 4.54f, 10.0f)
                curveTo(4.917f, 9.997f, 5.293f, 9.956f, 5.661f, 9.876f)
                curveTo(5.942f, 10.227f, 6.298f, 10.51f, 6.702f, 10.705f)
                curveTo(7.107f, 10.899f, 7.551f, 11.0f, 8.0f, 11.0f)
                horizontalLineTo(10.5f)
                curveTo(10.633f, 11.0f, 10.76f, 10.947f, 10.854f, 10.854f)
                curveTo(10.947f, 10.76f, 11.0f, 10.633f, 11.0f, 10.5f)
                verticalLineTo(8.0f)
                curveTo(11.0f, 7.551f, 10.899f, 7.107f, 10.704f, 6.702f)
                curveTo(10.509f, 6.297f, 10.225f, 5.942f, 9.874f, 5.661f)
                curveTo(9.954f, 5.293f, 9.997f, 4.917f, 10.0f, 4.54f)
                curveTo(10.608f, 4.891f, 11.112f, 5.395f, 11.463f, 6.002f)
                curveTo(11.814f, 6.61f, 11.999f, 7.299f, 12.0f, 8.0f)
                close()
                moveTo(8.988f, 4.826f)
                curveTo(9.035f, 4.182f, 8.943f, 3.535f, 8.717f, 2.93f)
                curveTo(8.492f, 2.324f, 8.139f, 1.775f, 7.682f, 1.318f)
                curveTo(7.226f, 0.861f, 6.676f, 0.508f, 6.071f, 0.283f)
                curveTo(5.465f, 0.058f, 4.819f, -0.035f, 4.174f, 0.012f)
                curveTo(3.033f, 0.142f, 1.978f, 0.685f, 1.21f, 1.539f)
                curveTo(0.441f, 2.393f, 0.011f, 3.498f, 0.0f, 4.647f)
                lineTo(0.0f, 7.167f)
                curveTo(0.0f, 8.433f, 0.753f, 9.0f, 1.5f, 9.0f)
                horizontalLineTo(4.35f)
                curveTo(5.499f, 8.99f, 6.606f, 8.56f, 7.46f, 7.791f)
                curveTo(8.314f, 7.022f, 8.858f, 5.968f, 8.988f, 4.826f)
                close()
                moveTo(6.975f, 2.026f)
                curveTo(7.33f, 2.382f, 7.604f, 2.809f, 7.78f, 3.28f)
                curveTo(7.955f, 3.751f, 8.027f, 4.254f, 7.991f, 4.756f)
                curveTo(7.884f, 5.648f, 7.455f, 6.47f, 6.785f, 7.067f)
                curveTo(6.114f, 7.665f, 5.248f, 7.996f, 4.35f, 8.0f)
                horizontalLineTo(1.5f)
                curveTo(1.036f, 8.0f, 1.0f, 7.363f, 1.0f, 7.167f)
                verticalLineTo(4.647f)
                curveTo(1.004f, 3.749f, 1.336f, 2.884f, 1.934f, 2.214f)
                curveTo(2.532f, 1.544f, 3.353f, 1.116f, 4.245f, 1.009f)
                curveTo(4.328f, 1.003f, 4.411f, 1.0f, 4.494f, 1.0f)
                curveTo(4.955f, 1.0f, 5.411f, 1.09f, 5.837f, 1.266f)
                curveTo(6.262f, 1.442f, 6.649f, 1.7f, 6.975f, 2.026f)
                close()
            }
        }
        .build()
        return _commentBorder!!
    }

private var _commentBorder: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.CommentBorder, contentDescription = "")
    }
}
