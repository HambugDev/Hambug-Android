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

public val AppIcons.ArrowRight: ImageVector
    get() {
        if (_arrowRight != null) {
            return _arrowRight!!
        }
        _arrowRight = Builder(name = "ArrowRight", defaultWidth = 5.0.dp, defaultHeight = 10.0.dp,
                viewportWidth = 5.0f, viewportHeight = 10.0f).apply {
            path(fill = SolidColor(Color(0xFF374957)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.267f, 3.587f)
                lineTo(1.207f, 0.527f)
                curveTo(1.082f, 0.402f, 0.913f, 0.333f, 0.737f, 0.333f)
                curveTo(0.56f, 0.333f, 0.391f, 0.402f, 0.267f, 0.527f)
                curveTo(0.204f, 0.589f, 0.154f, 0.662f, 0.121f, 0.744f)
                curveTo(0.087f, 0.825f, 0.069f, 0.912f, 0.069f, 1.0f)
                curveTo(0.069f, 1.088f, 0.087f, 1.175f, 0.121f, 1.256f)
                curveTo(0.154f, 1.338f, 0.204f, 1.411f, 0.267f, 1.473f)
                lineTo(3.333f, 4.527f)
                curveTo(3.396f, 4.589f, 3.445f, 4.662f, 3.479f, 4.744f)
                curveTo(3.513f, 4.825f, 3.53f, 4.912f, 3.53f, 5.0f)
                curveTo(3.53f, 5.088f, 3.513f, 5.175f, 3.479f, 5.256f)
                curveTo(3.445f, 5.338f, 3.396f, 5.411f, 3.333f, 5.473f)
                lineTo(0.267f, 8.527f)
                curveTo(0.141f, 8.651f, 0.07f, 8.821f, 0.069f, 8.998f)
                curveTo(0.069f, 9.175f, 0.139f, 9.344f, 0.263f, 9.47f)
                curveTo(0.388f, 9.595f, 0.557f, 9.666f, 0.734f, 9.667f)
                curveTo(0.911f, 9.668f, 1.081f, 9.598f, 1.207f, 9.473f)
                lineTo(4.267f, 6.413f)
                curveTo(4.641f, 6.038f, 4.851f, 5.53f, 4.851f, 5.0f)
                curveTo(4.851f, 4.47f, 4.641f, 3.962f, 4.267f, 3.587f)
                close()
            }
        }
        .build()
        return _arrowRight!!
    }

private var _arrowRight: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.ArrowRight, contentDescription = "")
    }
}
