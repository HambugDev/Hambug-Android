package desktop.hambug.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Community
import desktop.hambug.presentation.ui.icon.appicons.Home
import desktop.hambug.presentation.ui.icon.appicons.User
import desktop.hambug.presentation.ui.theme.HambugTheme

data class BottomNavItem(
    val icon: ImageVector,
    val label: String,
    val route: String
)

@Composable
fun HambugBottomNav(
    currentRoute: String,
    onNavItemClick: (String) -> Unit,
) {
    val items = listOf(
        BottomNavItem(AppIcons.Home, "홈", "home"),
        BottomNavItem(AppIcons.Community, "커뮤니티", "community"),
        BottomNavItem(AppIcons.User, "마이", "my")
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(thickness = 1.dp, color = HambugTheme.colors.bgNormal)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(HambugTheme.colors.bgWhite),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            // 리플효과 제거
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onNavItemClick(item.route)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(10.dp))
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (selected) HambugTheme.colors.primRed else HambugTheme.colors.borderDefault
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = item.label,
                        style = HambugTheme.typography.label02,
                        color = if (selected) HambugTheme.colors.primRed else HambugTheme.colors.borderDefault
                    )
                }
            }
        }
    }
}
