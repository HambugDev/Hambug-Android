package desktop.hambug.presentation.noti

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import desktop.hambug.presentation.component.EmptyStateView
import desktop.hambug.presentation.noti.component.NotiItem
import desktop.hambug.presentation.ui.component.HambugLoadingIndicator
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Back
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavHostController,
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {
    val uiState by notificationViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "알림",
                        style = HambugTheme.typography.title02,
                        color = HambugTheme.colors.textHeadline
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { navController.popBackStack() }
                            .padding(16.dp),
                        imageVector = AppIcons.Back,
                        contentDescription = null,
                        tint = HambugTheme.colors.iconDisabled,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = HambugTheme.colors.bgWhite
                )
            )
        }
    ) { paddingValues ->

        when (uiState) {
            is NotiUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    HambugLoadingIndicator()
                }
            }
            is NotiUiState.Error -> {}
            is NotiUiState.Success -> {
                val notis = (uiState as NotiUiState.Success).notis

                if (notis.isEmpty()) {
                    EmptyStateView(
                        modifier = Modifier.padding(paddingValues),
                        message = "아직 받은 알림이 없어요."
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(
                            items = notis,
                            key = { it.notiId }
                        ) { noti ->
                            NotiItem(
                                content = noti.content,
                                createdAt = noti.createdAt,
                                onClick = {
                                    navController.navigate("community_detail/${noti.targetId}")
                                }
                            )
                            Spacer(Modifier.height(16.dp))
                            HorizontalDivider(thickness = 1.dp, color = HambugTheme.colors.bgDarker)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NotificationScreenPreview() {
    HambugTheme {
//        NotificationScreen()
    }
}
