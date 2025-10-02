package desktop.hambug.presentation.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.R
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Activity
import desktop.hambug.presentation.ui.icon.appicons.ArrowRight
import desktop.hambug.presentation.ui.icon.appicons.Logout
import desktop.hambug.presentation.ui.icon.appicons.Pen
import desktop.hambug.presentation.ui.icon.appicons.Remove
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.presentation.ui.theme.RemoveRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MypageScreen() {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(   
                title = {
                    Text(
                        text = "마이페이지",
                        style = HambugTheme.typography.title02,
                        color = HambugTheme.colors.textHeadline
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = HambugTheme.colors.bgWhite
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // 상단 영역 (프로필이미지 + 닉네임)
            MypageHeaderSection()

            Spacer(Modifier.height(40.dp))

            // 메뉴 선택 영역
            MypageMenuSection()
        }
    }
}

@Composable
fun MypageHeaderSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))
        MypageProfileImage()
        Spacer(Modifier.height(24.dp))
        Text(
            text = "패티포터",
            style = HambugTheme.typography.body02,
            color = HambugTheme.colors.textBody
        )
    }
}

@Composable
fun MypageProfileImage() {
    Box(
        modifier = Modifier.size(140.dp)
    ) {
        // border를 별도 Box로 분리 (문제 해결)
        // - 부모 Box에 border를 그리면, Modifier 특성상 자식 요소들 위에 그려짐
        // - 이 때문에 border가 작은 Box(아이콘 영역) 위를 덮는 문제 발생
        Box(
            modifier = Modifier
                .size(140.dp)
                .border(width = 2.dp, color = HambugTheme.colors.primRed, shape = CircleShape)
        )

        Image(
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.logo_profile),
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-9).dp, y = (-5).dp)
                .size(30.dp)
                .background(color = HambugTheme.colors.primRed, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = AppIcons.Pen,
                contentDescription = null,
                tint = HambugTheme.colors.bgWhite
            )
        }
    }
}

@Composable
fun MypageMenuSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
    ) {
        MypageMenuButton(
            menuIcon = AppIcons.Activity,
            menuText = "활동 내역",
            onClick = {},
            modifier = Modifier
                .background(color = HambugTheme.colors.bgNormal, shape = RoundedCornerShape(4.dp))
                .padding(20.dp)
        )

        Spacer(Modifier.height(24.dp))

        MypageMenuButton(
            menuIcon = AppIcons.Logout,
            menuText = "로그아웃",
            onClick = {},
            modifier = Modifier
                .background(color = HambugTheme.colors.bgNormal, shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
        )

        MypageMenuButton(
            menuIcon = AppIcons.Remove,
            menuText = "탈퇴하기",
            onClick = {},
            modifier = Modifier
                .background(color = HambugTheme.colors.bgNormal, shape = RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp))
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
        )
    }
}

@Composable
fun MypageMenuButton(
    menuIcon: ImageVector,
    menuText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = menuIcon,
                contentDescription = null,
                tint = if (menuText == "탈퇴하기") RemoveRed else HambugTheme.colors.iconDisabled
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = menuText,
                style = HambugTheme.typography.body02,
                color = if (menuText == "탈퇴하기") RemoveRed else HambugTheme.colors.textBody
            )
        }

        Icon(
            modifier = Modifier.padding(end = 6.dp),
            imageVector = AppIcons.ArrowRight,
            contentDescription = null,
            tint = HambugTheme.colors.iconDefault
        )
    }
}

@Preview
@Composable
fun MypageScreenPreview() {
    HambugTheme {
        MypageScreen()
    }
}
