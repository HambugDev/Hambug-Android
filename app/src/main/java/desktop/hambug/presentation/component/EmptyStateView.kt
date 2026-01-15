package desktop.hambug.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import desktop.hambug.R
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun EmptyStateView(
    modifier: Modifier = Modifier,
    message: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(96.dp),
            painter = painterResource(id = R.drawable.logo_login),
            contentDescription = null,
            colorFilter = ColorFilter.tint(HambugTheme.colors.borderDisabled)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = message,
            style = HambugTheme.typography.body02,
            color = HambugTheme.colors.borderDefault
        )
    }
}
