package desktop.hambug.presentation.component.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.BellBorder

@Composable
fun NotiIcon(
    onClick: () -> Unit,
    tint: Color
) {
    Icon(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
            .size(32.dp),
        imageVector = AppIcons.BellBorder,
        contentDescription = null,
        tint = tint
    )
}
