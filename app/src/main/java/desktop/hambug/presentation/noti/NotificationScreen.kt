package desktop.hambug.presentation.noti

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import desktop.hambug.R
import desktop.hambug.presentation.noti.component.NotiItem
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Back
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavHostController,
    notificationViewModel: NotificationViewModel = hiltViewModel()
) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
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
                text = "아직 받은 알림이 없어요.",
                style = HambugTheme.typography.body02,
                color = HambugTheme.colors.borderDefault
            )
        }

        // 알림 아이템 1개 이상인 경우
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .padding(horizontal = 20.dp)
//        ) {
//            for (idx in 0 until 3) {
//                NotiItem(
//                    content = "라떼님이 내 게시물을 좋아합니다.",
//                    onClick = {},
//                    modifier = Modifier.padding(vertical = 14.dp)
//                )
//                NotiItem(
//                    content = "사랑에빠진햄버거피자님이 내 게시물을 좋아합니다.",
//                    onClick = {},
//                    modifier = Modifier.padding(vertical = 14.dp)
//                )
//                NotiItem(
//                    content = "사랑에빠진햄버거피자님이 내 게시물에 댓글을 달았습니다.",
//                    onClick = {},
//                    modifier = Modifier.padding(vertical = 14.dp)
//                )
//            }
//        }

        // 알림 아이템 없는 경우
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                modifier = Modifier.size(96.dp),
//                painter = painterResource(id = R.drawable.logo_login),
//                contentDescription = null,
//                colorFilter = ColorFilter.tint(HambugTheme.colors.borderDisabled)
//            )
//            Spacer(Modifier.height(16.dp))
//            Text(
//                text = "아직 받은 알림이 없어요.",
//                style = HambugTheme.typography.body02Prominent,
//                color = HambugTheme.colors.borderDefault
//            )
//        }
    }
}

@Preview
@Composable
fun NotificationScreenPreview() {
    HambugTheme {
//        NotificationScreen()
    }
}
