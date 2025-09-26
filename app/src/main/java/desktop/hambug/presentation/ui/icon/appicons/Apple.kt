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

public val AppIcons.Apple: ImageVector
    get() {
        if (_apple != null) {
            return _apple!!
        }
        _apple = Builder(name = "Apple", defaultWidth = 16.0.dp, defaultHeight = 18.0.dp,
                viewportWidth = 16.0f, viewportHeight = 18.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(15.212f, 13.121f)
                verticalLineTo(13.174f)
                curveTo(15.128f, 13.618f, 14.923f, 14.049f, 14.723f, 14.455f)
                curveTo(14.413f, 15.082f, 14.045f, 15.644f, 13.644f, 16.208f)
                curveTo(13.181f, 16.857f, 12.459f, 17.679f, 11.634f, 17.812f)
                curveTo(10.808f, 17.946f, 10.158f, 17.661f, 9.453f, 17.354f)
                curveTo(8.69f, 17.046f, 7.848f, 17.117f, 7.086f, 17.404f)
                lineTo(5.993f, 17.808f)
                lineTo(5.553f, 17.846f)
                curveTo(5.439f, 17.838f, 5.311f, 17.858f, 5.199f, 17.846f)
                curveTo(4.555f, 17.833f, 4.001f, 17.314f, 3.584f, 16.862f)
                curveTo(2.253f, 15.419f, 1.297f, 13.417f, 0.939f, 11.476f)
                curveTo(0.787f, 10.658f, 0.747f, 9.644f, 0.833f, 8.816f)
                curveTo(1.11f, 6.177f, 3.294f, 3.974f, 6.024f, 4.539f)
                curveTo(6.453f, 4.669f, 6.873f, 4.823f, 7.286f, 5.0f)
                curveTo(7.852f, 5.242f, 8.395f, 5.267f, 8.959f, 4.995f)
                curveTo(9.572f, 4.734f, 10.195f, 4.497f, 10.867f, 4.42f)
                curveTo(12.368f, 4.249f, 13.944f, 4.901f, 14.779f, 6.179f)
                curveTo(14.118f, 6.662f, 13.56f, 7.201f, 13.215f, 7.957f)
                curveTo(12.328f, 9.908f, 13.199f, 12.213f, 15.155f, 13.06f)
                lineTo(15.212f, 13.121f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.512f, 0.15f)
                curveTo(11.586f, 0.494f, 11.544f, 0.886f, 11.47f, 1.228f)
                curveTo(11.274f, 2.143f, 10.702f, 3.047f, 9.946f, 3.596f)
                curveTo(9.393f, 3.997f, 8.674f, 4.3f, 7.979f, 4.216f)
                curveTo(7.879f, 3.756f, 7.98f, 3.249f, 8.135f, 2.811f)
                curveTo(8.637f, 1.394f, 9.993f, 0.258f, 11.512f, 0.15f)
                close()
            }
        }
        .build()
        return _apple!!
    }

private var _apple: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Apple, contentDescription = "")
    }
}
