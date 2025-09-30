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

public val AppIcons.Comment: ImageVector
    get() {
        if (_comment != null) {
            return _comment!!
        }
        _comment = Builder(name = "Comment", defaultWidth = 12.0.dp, defaultHeight = 12.0.dp,
                viewportWidth = 12.0f, viewportHeight = 12.0f).apply {
            path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.35f, 9.0f)
                horizontalLineTo(1.5f)
                curveTo(0.753f, 9.0f, 0.0f, 8.433f, 0.0f, 7.167f)
                verticalLineTo(4.647f)
                curveTo(0.011f, 3.498f, 0.441f, 2.393f, 1.21f, 1.539f)
                curveTo(1.978f, 0.685f, 3.033f, 0.142f, 4.174f, 0.012f)
                curveTo(4.819f, -0.035f, 5.465f, 0.057f, 6.071f, 0.283f)
                curveTo(6.676f, 0.508f, 7.226f, 0.861f, 7.682f, 1.318f)
                curveTo(8.139f, 1.774f, 8.492f, 2.324f, 8.717f, 2.929f)
                curveTo(8.943f, 3.535f, 9.035f, 4.181f, 8.988f, 4.826f)
                curveTo(8.858f, 5.968f, 8.314f, 7.022f, 7.46f, 7.791f)
                curveTo(6.606f, 8.56f, 5.499f, 8.99f, 4.35f, 9.0f)
                close()
                moveTo(10.0f, 4.54f)
                horizontalLineTo(9.994f)
                curveTo(9.994f, 4.659f, 9.994f, 4.777f, 9.988f, 4.896f)
                curveTo(9.795f, 7.6f, 7.324f, 9.889f, 4.542f, 9.991f)
                verticalLineTo(9.998f)
                curveTo(4.892f, 10.606f, 5.396f, 11.11f, 6.003f, 11.462f)
                curveTo(6.61f, 11.813f, 7.299f, 11.999f, 8.0f, 12.0f)
                horizontalLineTo(10.5f)
                curveTo(10.898f, 12.0f, 11.279f, 11.842f, 11.561f, 11.561f)
                curveTo(11.842f, 11.279f, 12.0f, 10.898f, 12.0f, 10.5f)
                verticalLineTo(8.0f)
                curveTo(11.999f, 7.299f, 11.814f, 6.61f, 11.463f, 6.002f)
                curveTo(11.112f, 5.395f, 10.608f, 4.891f, 10.0f, 4.54f)
                close()
            }
        }
        .build()
        return _comment!!
    }

private var _comment: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Comment, contentDescription = "")
    }
}
