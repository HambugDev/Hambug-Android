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

public val AppIcons.BellBorder: ImageVector
    get() {
        if (_bellBorder != null) {
            return _bellBorder!!
        }
        _bellBorder = Builder(name = "BellBorder", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF1C1917)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(13.729f, 18.0f)
                curveTo(13.643f, 18.15f, 13.538f, 18.29f, 13.414f, 18.414f)
                curveTo(13.039f, 18.789f, 12.53f, 19.0f, 12.0f, 19.0f)
                curveTo(11.47f, 19.0f, 10.961f, 18.789f, 10.586f, 18.414f)
                curveTo(10.462f, 18.29f, 10.357f, 18.15f, 10.271f, 18.0f)
                horizontalLineTo(13.729f)
                close()
                moveTo(12.0f, 5.0f)
                curveTo(13.326f, 5.0f, 14.598f, 5.527f, 15.536f, 6.465f)
                curveTo(16.474f, 7.402f, 17.0f, 8.674f, 17.0f, 10.0f)
                verticalLineTo(14.0f)
                lineTo(18.0f, 15.0f)
                horizontalLineTo(6.0f)
                lineTo(7.0f, 14.0f)
                verticalLineTo(10.0f)
                curveTo(7.0f, 8.674f, 7.526f, 7.402f, 8.464f, 6.465f)
                curveTo(9.402f, 5.527f, 10.674f, 5.0f, 12.0f, 5.0f)
                close()
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
