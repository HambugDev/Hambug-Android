package desktop.hambug.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Pen
import desktop.hambug.presentation.ui.theme.FABRed
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(color = FABRed, shape = CircleShape)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = AppIcons.Pen,
            contentDescription = null,
            tint = HambugTheme.colors.bgWhite
        )
    }
}
