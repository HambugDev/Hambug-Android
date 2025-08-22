package desktop.hambug.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import desktop.hambug.ui.theme.HambugTheme
import desktop.hambug.ui.theme.spacing

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "안녕하세요 테스트입니다",
            style = MaterialTheme.typography.headlineMedium,
            color = HambugTheme.colors.primRed
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        Text(
            text = "hello test",
            style = MaterialTheme.typography.bodyLarge,
            color = HambugTheme.colors.secondGreen
        )
    }
}
