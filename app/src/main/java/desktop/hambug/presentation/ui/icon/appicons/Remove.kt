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

public val AppIcons.Remove: ImageVector
    get() {
        if (_remove != null) {
            return _remove!!
        }
        _remove = Builder(name = "Remove", defaultWidth = 16.0.dp, defaultHeight = 16.0.dp,
                viewportWidth = 16.0f, viewportHeight = 16.0f).apply {
            path(fill = SolidColor(Color(0xFFF65555)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(2.0f, 4.0f)
                curveTo(2.0f, 3.209f, 2.235f, 2.436f, 2.674f, 1.778f)
                curveTo(3.114f, 1.12f, 3.738f, 0.607f, 4.469f, 0.304f)
                curveTo(5.2f, 0.002f, 6.004f, -0.077f, 6.78f, 0.077f)
                curveTo(7.556f, 0.231f, 8.269f, 0.612f, 8.828f, 1.172f)
                curveTo(9.388f, 1.731f, 9.769f, 2.444f, 9.923f, 3.22f)
                curveTo(10.078f, 3.996f, 9.998f, 4.8f, 9.696f, 5.531f)
                curveTo(9.393f, 6.262f, 8.88f, 6.886f, 8.222f, 7.326f)
                curveTo(7.564f, 7.765f, 6.791f, 8.0f, 6.0f, 8.0f)
                curveTo(4.939f, 7.999f, 3.923f, 7.577f, 3.173f, 6.827f)
                curveTo(2.423f, 6.077f, 2.001f, 5.061f, 2.0f, 4.0f)
                close()
                moveTo(6.0f, 9.333f)
                curveTo(4.409f, 9.335f, 2.884f, 9.968f, 1.759f, 11.093f)
                curveTo(0.634f, 12.217f, 0.002f, 13.743f, 0.0f, 15.333f)
                curveTo(0.0f, 15.51f, 0.07f, 15.68f, 0.195f, 15.805f)
                curveTo(0.32f, 15.93f, 0.49f, 16.0f, 0.667f, 16.0f)
                horizontalLineTo(11.333f)
                curveTo(11.51f, 16.0f, 11.68f, 15.93f, 11.805f, 15.805f)
                curveTo(11.93f, 15.68f, 12.0f, 15.51f, 12.0f, 15.333f)
                curveTo(11.998f, 13.743f, 11.366f, 12.217f, 10.241f, 11.093f)
                curveTo(9.116f, 9.968f, 7.591f, 9.335f, 6.0f, 9.333f)
                close()
                moveTo(14.276f, 8.0f)
                lineTo(15.805f, 6.471f)
                curveTo(15.926f, 6.346f, 15.993f, 6.177f, 15.992f, 6.002f)
                curveTo(15.99f, 5.828f, 15.92f, 5.66f, 15.797f, 5.537f)
                curveTo(15.673f, 5.413f, 15.506f, 5.343f, 15.331f, 5.342f)
                curveTo(15.156f, 5.34f, 14.988f, 5.407f, 14.862f, 5.529f)
                lineTo(13.333f, 7.057f)
                lineTo(11.805f, 5.529f)
                curveTo(11.679f, 5.407f, 11.51f, 5.34f, 11.336f, 5.342f)
                curveTo(11.161f, 5.343f, 10.994f, 5.413f, 10.87f, 5.537f)
                curveTo(10.747f, 5.66f, 10.676f, 5.828f, 10.675f, 6.002f)
                curveTo(10.673f, 6.177f, 10.741f, 6.346f, 10.862f, 6.471f)
                lineTo(12.391f, 8.0f)
                lineTo(10.862f, 9.529f)
                curveTo(10.798f, 9.59f, 10.748f, 9.664f, 10.713f, 9.745f)
                curveTo(10.678f, 9.826f, 10.659f, 9.914f, 10.658f, 10.002f)
                curveTo(10.658f, 10.091f, 10.675f, 10.179f, 10.708f, 10.261f)
                curveTo(10.742f, 10.343f, 10.791f, 10.417f, 10.854f, 10.48f)
                curveTo(10.916f, 10.542f, 10.991f, 10.592f, 11.073f, 10.625f)
                curveTo(11.155f, 10.659f, 11.242f, 10.676f, 11.331f, 10.675f)
                curveTo(11.42f, 10.674f, 11.507f, 10.656f, 11.588f, 10.621f)
                curveTo(11.67f, 10.586f, 11.743f, 10.535f, 11.805f, 10.471f)
                lineTo(13.333f, 8.943f)
                lineTo(14.862f, 10.471f)
                curveTo(14.988f, 10.593f, 15.156f, 10.66f, 15.331f, 10.658f)
                curveTo(15.506f, 10.657f, 15.673f, 10.587f, 15.797f, 10.463f)
                curveTo(15.92f, 10.34f, 15.99f, 10.172f, 15.992f, 9.998f)
                curveTo(15.993f, 9.823f, 15.926f, 9.654f, 15.805f, 9.529f)
                lineTo(14.276f, 8.0f)
                close()
            }
        }
        .build()
        return _remove!!
    }

private var _remove: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Remove, contentDescription = "")
    }
}
