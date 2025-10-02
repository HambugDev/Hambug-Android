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

public val AppIcons.Logout: ImageVector
    get() {
        if (_logout != null) {
            return _logout!!
        }
        _logout = Builder(name = "Logout", defaultWidth = 16.0.dp, defaultHeight = 16.0.dp,
                viewportWidth = 16.0f, viewportHeight = 16.0f).apply {
            path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(15.221f, 6.114f)
                lineTo(12.635f, 3.528f)
                curveTo(12.37f, 3.272f, 11.948f, 3.28f, 11.692f, 3.544f)
                curveTo(11.442f, 3.803f, 11.442f, 4.212f, 11.692f, 4.471f)
                lineTo(14.278f, 7.057f)
                curveTo(14.355f, 7.135f, 14.422f, 7.222f, 14.478f, 7.317f)
                curveTo(14.468f, 7.317f, 14.46f, 7.311f, 14.45f, 7.311f)
                lineTo(3.994f, 7.333f)
                curveTo(3.626f, 7.333f, 3.327f, 7.631f, 3.327f, 7.999f)
                curveTo(3.327f, 8.367f, 3.626f, 8.666f, 3.994f, 8.666f)
                lineTo(14.446f, 8.645f)
                curveTo(14.465f, 8.645f, 14.48f, 8.635f, 14.498f, 8.634f)
                curveTo(14.439f, 8.747f, 14.364f, 8.85f, 14.275f, 8.942f)
                lineTo(11.689f, 11.528f)
                curveTo(11.424f, 11.784f, 11.417f, 12.206f, 11.673f, 12.471f)
                curveTo(11.929f, 12.736f, 12.351f, 12.743f, 12.616f, 12.487f)
                curveTo(12.621f, 12.482f, 12.627f, 12.476f, 12.632f, 12.471f)
                lineTo(15.218f, 9.885f)
                curveTo(16.259f, 8.843f, 16.259f, 7.155f, 15.218f, 6.114f)
                horizontalLineTo(15.221f)
                close()
            }
            path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.668f, 14.667f)
                horizontalLineTo(3.335f)
                curveTo(2.23f, 14.667f, 1.335f, 13.771f, 1.335f, 12.667f)
                verticalLineTo(3.333f)
                curveTo(1.335f, 2.229f, 2.23f, 1.333f, 3.335f, 1.333f)
                horizontalLineTo(4.668f)
                curveTo(5.036f, 1.333f, 5.335f, 1.035f, 5.335f, 0.667f)
                curveTo(5.335f, 0.299f, 5.036f, 0.0f, 4.668f, 0.0f)
                horizontalLineTo(3.335f)
                curveTo(1.495f, 0.002f, 0.004f, 1.493f, 0.001f, 3.333f)
                verticalLineTo(12.667f)
                curveTo(0.004f, 14.507f, 1.495f, 15.998f, 3.335f, 16.0f)
                horizontalLineTo(4.668f)
                curveTo(5.036f, 16.0f, 5.335f, 15.701f, 5.335f, 15.333f)
                curveTo(5.335f, 14.965f, 5.036f, 14.667f, 4.668f, 14.667f)
                close()
            }
        }
        .build()
        return _logout!!
    }

private var _logout: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Logout, contentDescription = "")
    }
}
