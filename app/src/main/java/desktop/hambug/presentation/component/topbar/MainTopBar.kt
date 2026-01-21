package desktop.hambug.presentation.component.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun MainTopBar(
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    containerColor: Color = HambugTheme.colors.bgWhite,
) {
    BaseTopBar(
        title = title,
        actions = actions,
        containerColor = containerColor
    )
}
