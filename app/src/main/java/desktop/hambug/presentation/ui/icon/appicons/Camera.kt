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

public val AppIcons.Camera: ImageVector
    get() {
        if (_camera != null) {
            return _camera!!
        }
        _camera = Builder(name = "Camera", defaultWidth = 15.0.dp, defaultHeight = 15.0.dp,
                viewportWidth = 15.0f, viewportHeight = 15.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFEC6D55)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(11.583f, 2.833f)
                    horizontalLineTo(11.287f)
                    lineTo(10.013f, 1.181f)
                    curveTo(9.849f, 0.97f, 9.639f, 0.799f, 9.399f, 0.681f)
                    curveTo(9.158f, 0.563f, 8.895f, 0.501f, 8.627f, 0.5f)
                    lineTo(6.373f, 0.5f)
                    curveTo(6.105f, 0.501f, 5.842f, 0.563f, 5.601f, 0.681f)
                    curveTo(5.361f, 0.799f, 5.151f, 0.97f, 4.987f, 1.181f)
                    lineTo(3.713f, 2.833f)
                    horizontalLineTo(3.417f)
                    curveTo(2.643f, 2.834f, 1.902f, 3.142f, 1.355f, 3.689f)
                    curveTo(0.809f, 4.235f, 0.501f, 4.977f, 0.5f, 5.75f)
                    lineTo(0.5f, 11.583f)
                    curveTo(0.501f, 12.357f, 0.809f, 13.098f, 1.355f, 13.645f)
                    curveTo(1.902f, 14.191f, 2.643f, 14.499f, 3.417f, 14.5f)
                    horizontalLineTo(11.583f)
                    curveTo(12.357f, 14.499f, 13.098f, 14.191f, 13.645f, 13.645f)
                    curveTo(14.191f, 13.098f, 14.499f, 12.357f, 14.5f, 11.583f)
                    verticalLineTo(5.75f)
                    curveTo(14.499f, 4.977f, 14.191f, 4.235f, 13.645f, 3.689f)
                    curveTo(13.098f, 3.142f, 12.357f, 2.834f, 11.583f, 2.833f)
                    close()
                    moveTo(5.911f, 1.894f)
                    curveTo(5.966f, 1.824f, 6.036f, 1.766f, 6.116f, 1.727f)
                    curveTo(6.196f, 1.688f, 6.284f, 1.667f, 6.373f, 1.667f)
                    horizontalLineTo(8.627f)
                    curveTo(8.716f, 1.667f, 8.804f, 1.688f, 8.884f, 1.727f)
                    curveTo(8.964f, 1.767f, 9.034f, 1.824f, 9.089f, 1.894f)
                    lineTo(9.814f, 2.833f)
                    horizontalLineTo(5.187f)
                    lineTo(5.911f, 1.894f)
                    close()
                    moveTo(13.333f, 11.583f)
                    curveTo(13.333f, 12.047f, 13.149f, 12.493f, 12.821f, 12.821f)
                    curveTo(12.493f, 13.149f, 12.047f, 13.333f, 11.583f, 13.333f)
                    horizontalLineTo(3.417f)
                    curveTo(2.953f, 13.333f, 2.507f, 13.149f, 2.179f, 12.821f)
                    curveTo(1.851f, 12.493f, 1.667f, 12.047f, 1.667f, 11.583f)
                    verticalLineTo(5.75f)
                    curveTo(1.667f, 5.286f, 1.851f, 4.841f, 2.179f, 4.513f)
                    curveTo(2.507f, 4.184f, 2.953f, 4.0f, 3.417f, 4.0f)
                    horizontalLineTo(11.583f)
                    curveTo(12.047f, 4.0f, 12.493f, 4.184f, 12.821f, 4.513f)
                    curveTo(13.149f, 4.841f, 13.333f, 5.286f, 13.333f, 5.75f)
                    verticalLineTo(11.583f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFEC6D55)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(7.5f, 5.167f)
                    curveTo(6.808f, 5.167f, 6.131f, 5.372f, 5.555f, 5.756f)
                    curveTo(4.98f, 6.141f, 4.531f, 6.688f, 4.266f, 7.327f)
                    curveTo(4.001f, 7.967f, 3.932f, 8.67f, 4.067f, 9.349f)
                    curveTo(4.202f, 10.028f, 4.536f, 10.652f, 5.025f, 11.141f)
                    curveTo(5.515f, 11.631f, 6.138f, 11.964f, 6.817f, 12.099f)
                    curveTo(7.496f, 12.234f, 8.2f, 12.165f, 8.839f, 11.9f)
                    curveTo(9.479f, 11.635f, 10.026f, 11.187f, 10.41f, 10.611f)
                    curveTo(10.795f, 10.035f, 11.0f, 9.359f, 11.0f, 8.667f)
                    curveTo(10.999f, 7.739f, 10.63f, 6.849f, 9.974f, 6.193f)
                    curveTo(9.318f, 5.536f, 8.428f, 5.167f, 7.5f, 5.167f)
                    close()
                    moveTo(7.5f, 11.0f)
                    curveTo(7.038f, 11.0f, 6.587f, 10.863f, 6.204f, 10.607f)
                    curveTo(5.82f, 10.35f, 5.521f, 9.986f, 5.344f, 9.559f)
                    curveTo(5.168f, 9.133f, 5.121f, 8.664f, 5.211f, 8.211f)
                    curveTo(5.301f, 7.759f, 5.524f, 7.343f, 5.85f, 7.017f)
                    curveTo(6.176f, 6.69f, 6.592f, 6.468f, 7.045f, 6.378f)
                    curveTo(7.497f, 6.288f, 7.967f, 6.334f, 8.393f, 6.511f)
                    curveTo(8.819f, 6.687f, 9.184f, 6.986f, 9.44f, 7.37f)
                    curveTo(9.696f, 7.754f, 9.833f, 8.205f, 9.833f, 8.667f)
                    curveTo(9.833f, 9.285f, 9.587f, 9.879f, 9.15f, 10.316f)
                    curveTo(8.712f, 10.754f, 8.119f, 11.0f, 7.5f, 11.0f)
                    close()
                }
            }
        }
        .build()
        return _camera!!
    }

private var _camera: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Camera, contentDescription = "")
    }
}
