package desktop.hambug.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val albums by viewModel.albums.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAlbums(userId = 1)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Text(
//            text = "안녕하세요 테스트입니다",
//            style = HambugTheme.typography.heading01,
//            color = HambugTheme.colors.primRed
//        )
//
//        Spacer(modifier = Modifier.height(HambugTheme.spacing.large))
//
//        Text(
//            text = "hello test",
//            style = HambugTheme.typography.body01,
//            color = HambugTheme.colors.secondGreen
//        )
//
//        Spacer(modifier = Modifier.height(HambugTheme.spacing.large))

        albums.forEach { album ->
            Text(text = "ID : ${album.id}, title = ${album.title}")
        }
    }
}
