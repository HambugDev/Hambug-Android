package desktop.hambug.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Bell
import desktop.hambug.presentation.ui.icon.appicons.Hambug
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val albums by viewModel.albums.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAlbums(userId = 1)
    }

    Scaffold(
        containerColor = HambugTheme.colors.bgNormal,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = HambugTheme.colors.primRed
                ),
                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .clickable {  }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = AppIcons.Hambug,
                            contentDescription = null,
                            tint = HambugTheme.colors.bgWhite
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "햄버그",
                            style = HambugTheme.typography.title02,
                            color = HambugTheme.colors.bgWhite
                        )
                    }
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .clickable { navController.navigate("bell") }
                            .padding(16.dp),
                        imageVector = AppIcons.Bell,
                        contentDescription = null,
                        tint = HambugTheme.colors.bgWhite
                    )
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(20.dp))
            Text(
                text = "오늘의 추천 버거",
                style = HambugTheme.typography.title02,
                color = HambugTheme.colors.textBody
            )
            Spacer(Modifier.height(200.dp))
            Text(
                text = "인기글",
                style = HambugTheme.typography.title02,
                color = HambugTheme.colors.textBody
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HambugTheme {
//        HomeScreen()
    }
}
