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
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.icon.AppIcons
import kotlin.Unit

public val AppIcons.Dots: ImageVector
    get() {
        if (_dots != null) {
            return _dots!!
        }
        _dots = Builder(name = "Dots", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFEEF1F4)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.0f, 0.0f)
                lineTo(12.0f, 0.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 24.0f, 12.0f)
                lineTo(24.0f, 12.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 12.0f, 24.0f)
                lineTo(12.0f, 24.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 0.0f, 12.0f)
                lineTo(0.0f, 12.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 12.0f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF545F71)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(12.5f, 8.125f)
                lineTo(12.5f, 8.131f)
                moveTo(12.5f, 12.5f)
                lineTo(12.5f, 12.506f)
                moveTo(12.5f, 16.875f)
                lineTo(12.5f, 16.881f)
                moveTo(12.5f, 8.75f)
                curveTo(12.155f, 8.75f, 11.875f, 8.47f, 11.875f, 8.125f)
                curveTo(11.875f, 7.78f, 12.155f, 7.5f, 12.5f, 7.5f)
                curveTo(12.845f, 7.5f, 13.125f, 7.78f, 13.125f, 8.125f)
                curveTo(13.125f, 8.47f, 12.845f, 8.75f, 12.5f, 8.75f)
                close()
                moveTo(12.5f, 13.125f)
                curveTo(12.155f, 13.125f, 11.875f, 12.845f, 11.875f, 12.5f)
                curveTo(11.875f, 12.155f, 12.155f, 11.875f, 12.5f, 11.875f)
                curveTo(12.845f, 11.875f, 13.125f, 12.155f, 13.125f, 12.5f)
                curveTo(13.125f, 12.845f, 12.845f, 13.125f, 12.5f, 13.125f)
                close()
                moveTo(12.5f, 17.5f)
                curveTo(12.155f, 17.5f, 11.875f, 17.22f, 11.875f, 16.875f)
                curveTo(11.875f, 16.53f, 12.155f, 16.25f, 12.5f, 16.25f)
                curveTo(12.845f, 16.25f, 13.125f, 16.53f, 13.125f, 16.875f)
                curveTo(13.125f, 17.22f, 12.845f, 17.5f, 12.5f, 17.5f)
                close()
            }
        }
        .build()
        return _dots!!
    }

private var _dots: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Dots, contentDescription = "")
    }
}
