package desktop.hambug.presentation.component.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Back
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun BackTopBar(
    title: @Composable () -> Unit,
    onBackClick: () -> Unit,
    containerColor: Color = HambugTheme.colors.bgWhite,
) {
    BaseTopBar(
        title = title,
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .clickable { onBackClick() }
                    .padding(16.dp),
                imageVector = AppIcons.Back,
                contentDescription = null,
                tint = HambugTheme.colors.iconDisabled,
            )
        },
        containerColor = containerColor,
        isCenterAligned = true
    )
}
