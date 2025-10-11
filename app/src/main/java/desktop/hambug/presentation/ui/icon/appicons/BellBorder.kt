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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.icon.AppIcons
import kotlin.Unit

public val AppIcons.BellBorder: ImageVector
    get() {
        if (_bellBorder != null) {
            return _bellBorder!!
        }
        _bellBorder = Builder(name = "BellBorder", defaultWidth = 18.0.dp, defaultHeight = 19.0.dp,
                viewportWidth = 18.0f, viewportHeight = 19.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(16.916f, 10.747f)
                    lineTo(15.491f, 5.62f)
                    curveTo(15.073f, 4.118f, 14.166f, 2.798f, 12.912f, 1.871f)
                    curveTo(11.659f, 0.945f, 10.131f, 0.463f, 8.573f, 0.504f)
                    curveTo(7.014f, 0.545f, 5.514f, 1.106f, 4.311f, 2.097f)
                    curveTo(3.108f, 3.088f, 2.27f, 4.453f, 1.932f, 5.975f)
                    lineTo(0.829f, 10.936f)
                    curveTo(0.707f, 11.484f, 0.71f, 12.053f, 0.837f, 12.6f)
                    curveTo(0.964f, 13.147f, 1.213f, 13.658f, 1.564f, 14.096f)
                    curveTo(1.916f, 14.535f, 2.361f, 14.888f, 2.867f, 15.131f)
                    curveTo(3.373f, 15.374f, 3.928f, 15.5f, 4.489f, 15.5f)
                    horizontalLineTo(5.325f)
                    curveTo(5.497f, 16.348f, 5.957f, 17.11f, 6.627f, 17.657f)
                    curveTo(7.296f, 18.205f, 8.135f, 18.504f, 9.0f, 18.504f)
                    curveTo(9.865f, 18.504f, 10.703f, 18.205f, 11.373f, 17.657f)
                    curveTo(12.043f, 17.11f, 12.503f, 16.348f, 12.675f, 15.5f)
                    horizontalLineTo(13.303f)
                    curveTo(13.882f, 15.5f, 14.452f, 15.366f, 14.97f, 15.11f)
                    curveTo(15.488f, 14.853f, 15.939f, 14.48f, 16.289f, 14.019f)
                    curveTo(16.639f, 13.559f, 16.878f, 13.024f, 16.986f, 12.457f)
                    curveTo(17.095f, 11.889f, 17.072f, 11.304f, 16.917f, 10.747f)
                    horizontalLineTo(16.916f)
                    close()
                    moveTo(9.0f, 17.0f)
                    curveTo(8.536f, 16.998f, 8.084f, 16.853f, 7.706f, 16.584f)
                    curveTo(7.328f, 16.316f, 7.042f, 15.937f, 6.888f, 15.5f)
                    horizontalLineTo(11.112f)
                    curveTo(10.957f, 15.937f, 10.672f, 16.316f, 10.293f, 16.584f)
                    curveTo(9.915f, 16.853f, 9.464f, 16.998f, 9.0f, 17.0f)
                    close()
                    moveTo(15.094f, 13.111f)
                    curveTo(14.885f, 13.389f, 14.615f, 13.613f, 14.304f, 13.767f)
                    curveTo(13.993f, 13.922f, 13.65f, 14.001f, 13.303f, 14.0f)
                    horizontalLineTo(4.489f)
                    curveTo(4.153f, 14.0f, 3.82f, 13.924f, 3.516f, 13.778f)
                    curveTo(3.212f, 13.633f, 2.945f, 13.42f, 2.735f, 13.158f)
                    curveTo(2.524f, 12.895f, 2.375f, 12.588f, 2.298f, 12.26f)
                    curveTo(2.222f, 11.932f, 2.22f, 11.591f, 2.293f, 11.262f)
                    lineTo(3.396f, 6.3f)
                    curveTo(3.662f, 5.104f, 4.319f, 4.032f, 5.265f, 3.254f)
                    curveTo(6.21f, 2.475f, 7.388f, 2.035f, 8.612f, 2.003f)
                    curveTo(9.836f, 1.97f, 11.036f, 2.349f, 12.02f, 3.077f)
                    curveTo(13.005f, 3.805f, 13.718f, 4.841f, 14.046f, 6.021f)
                    lineTo(15.471f, 11.148f)
                    curveTo(15.565f, 11.482f, 15.58f, 11.833f, 15.515f, 12.174f)
                    curveTo(15.449f, 12.515f, 15.306f, 12.836f, 15.094f, 13.111f)
                    close()
                }
            }
        }
        .build()
        return _bellBorder!!
    }

private var _bellBorder: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.BellBorder, contentDescription = "")
    }
}
