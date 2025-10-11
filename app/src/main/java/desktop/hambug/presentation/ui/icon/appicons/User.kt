package desktop.hambug.presentation.ui.icon.appicons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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

public val AppIcons.User: ImageVector
    get() {
        if (_user != null) {
            return _user!!
        }
        _user = Builder(name = "User", defaultWidth = 20.0.dp, defaultHeight = 21.0.dp,
                viewportWidth = 20.0f, viewportHeight = 21.0f).apply {
            path(fill = SolidColor(Color(0xFFA8A29E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(0.88f, 17.972f)
                verticalLineTo(16.501f)
                curveTo(0.938f, 15.409f, 1.621f, 14.438f, 2.653f, 13.979f)
                curveTo(7.522f, 12.098f, 12.958f, 12.098f, 17.827f, 13.979f)
                curveTo(18.86f, 14.438f, 19.542f, 15.409f, 19.6f, 16.501f)
                verticalLineTo(17.972f)
                curveTo(19.6f, 18.768f, 19.27f, 19.532f, 18.684f, 20.094f)
                curveTo(18.098f, 20.657f, 17.303f, 20.972f, 16.475f, 20.971f)
                horizontalLineTo(4.005f)
                curveTo(3.177f, 20.972f, 2.382f, 20.657f, 1.796f, 20.094f)
                curveTo(1.21f, 19.532f, 0.881f, 18.768f, 0.88f, 17.972f)
                close()
            }
            path(fill = SolidColor(Color(0xFFA8A29E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(10.24f, 0.5f)
                curveTo(8.136f, 0.499f, 6.24f, 1.717f, 5.434f, 3.586f)
                curveTo(4.629f, 5.455f, 5.073f, 7.606f, 6.561f, 9.036f)
                curveTo(8.048f, 10.467f, 10.285f, 10.895f, 12.229f, 10.121f)
                curveTo(14.172f, 9.347f, 15.439f, 7.524f, 15.439f, 5.501f)
                curveTo(15.44f, 4.175f, 14.892f, 2.903f, 13.917f, 1.965f)
                curveTo(12.942f, 1.027f, 11.619f, 0.5f, 10.24f, 0.5f)
                close()
            }
        }
        .build()
        return _user!!
    }

private var _user: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.User, contentDescription = "")
    }
}
