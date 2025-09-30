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

public val AppIcons.CommentDetail: ImageVector
    get() {
        if (_commentDetail != null) {
            return _commentDetail!!
        }
        _commentDetail = Builder(name = "CommentDetail", defaultWidth = 23.0.dp, defaultHeight =
                23.0.dp, viewportWidth = 23.0f, viewportHeight = 23.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF78716C)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(8.337f, 17.25f)
                    horizontalLineTo(2.875f)
                    curveTo(1.444f, 17.25f, 0.0f, 16.163f, 0.0f, 13.737f)
                    verticalLineTo(8.907f)
                    curveTo(0.02f, 6.705f, 0.845f, 4.586f, 2.318f, 2.949f)
                    curveTo(3.792f, 1.313f, 5.813f, 0.272f, 8.001f, 0.022f)
                    curveTo(9.236f, -0.068f, 10.475f, 0.109f, 11.635f, 0.541f)
                    curveTo(12.795f, 0.973f, 13.849f, 1.65f, 14.724f, 2.525f)
                    curveTo(15.6f, 3.401f, 16.276f, 4.454f, 16.708f, 5.614f)
                    curveTo(17.14f, 6.775f, 17.318f, 8.014f, 17.228f, 9.249f)
                    curveTo(16.978f, 11.438f, 15.936f, 13.459f, 14.298f, 14.933f)
                    curveTo(12.661f, 16.407f, 10.54f, 17.231f, 8.337f, 17.25f)
                    close()
                    moveTo(19.167f, 8.702f)
                    horizontalLineTo(19.155f)
                    curveTo(19.155f, 8.929f, 19.155f, 9.156f, 19.144f, 9.384f)
                    curveTo(18.774f, 14.566f, 14.037f, 18.954f, 8.705f, 19.148f)
                    verticalLineTo(19.163f)
                    curveTo(9.377f, 20.327f, 10.343f, 21.295f, 11.506f, 21.968f)
                    curveTo(12.669f, 22.642f, 13.989f, 22.997f, 15.333f, 23.0f)
                    horizontalLineTo(20.125f)
                    curveTo(20.888f, 23.0f, 21.619f, 22.697f, 22.158f, 22.158f)
                    curveTo(22.697f, 21.619f, 23.0f, 20.887f, 23.0f, 20.125f)
                    verticalLineTo(15.333f)
                    curveTo(22.999f, 13.989f, 22.644f, 12.668f, 21.971f, 11.504f)
                    curveTo(21.298f, 10.34f, 20.331f, 9.374f, 19.167f, 8.702f)
                    close()
                }
            }
        }
        .build()
        return _commentDetail!!
    }

private var _commentDetail: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.CommentDetail, contentDescription = "")
    }
}
