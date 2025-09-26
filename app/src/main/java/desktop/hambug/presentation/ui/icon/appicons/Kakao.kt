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

public val AppIcons.Kakao: ImageVector
    get() {
        if (_kakao != null) {
            return _kakao!!
        }
        _kakao = Builder(name = "Kakao", defaultWidth = 22.0.dp, defaultHeight = 22.0.dp,
                viewportWidth = 22.0f, viewportHeight = 22.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(18.639f, 3.455f)
                curveTo(14.757f, 0.434f, 8.784f, 0.196f, 4.558f, 2.647f)
                curveTo(1.655f, 4.331f, -0.384f, 7.337f, 0.264f, 10.818f)
                curveTo(0.761f, 13.491f, 2.845f, 15.646f, 5.235f, 16.785f)
                lineTo(4.133f, 20.674f)
                curveTo(4.107f, 20.757f, 4.13f, 20.908f, 4.194f, 20.967f)
                curveTo(4.37f, 21.129f, 4.754f, 20.873f, 4.921f, 20.777f)
                curveTo(6.345f, 19.964f, 7.677f, 18.875f, 9.057f, 17.986f)
                curveTo(12.826f, 18.478f, 17.111f, 17.458f, 19.753f, 14.614f)
                curveTo(23.032f, 11.085f, 22.34f, 6.334f, 18.639f, 3.455f)
                close()
            }
        }
        .build()
        return _kakao!!
    }

private var _kakao: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Kakao, contentDescription = "")
    }
}
