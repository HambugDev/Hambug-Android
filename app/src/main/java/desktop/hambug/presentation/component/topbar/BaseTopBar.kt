package desktop.hambug.presentation.component.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    containerColor: Color = HambugTheme.colors.bgWhite,
    isCenterAligned: Boolean = false
) {
    val colors = TopAppBarDefaults.topAppBarColors(
        containerColor = containerColor
    )

    if (isCenterAligned) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = title,
            navigationIcon = navigationIcon,
            colors = colors
        )
    } else {
        TopAppBar(
            modifier = modifier,
            title = title,
            actions = actions,
            colors = colors
        )
    }
}
