package desktop.hambug.presentation.community

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.community.component.RequiredFieldTitle
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Camera
import desktop.hambug.presentation.ui.icon.appicons.CircleCross
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostWriteScreen() {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "게시물 작성",
                        style = HambugTheme.typography.title02,
                        color = HambugTheme.colors.textHeadline
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 16.dp),
                        imageVector = Icons.Default.Home,
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
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            RequiredFieldTitle(title = "카테고리")

            Spacer(modifier = Modifier.height(12.dp))

            // 카테고리 목록
            Row {
                Box(
                    modifier = Modifier
                        .height(34.dp)
                        .background(color = HambugTheme.colors.bgWhite)
                        .border(width = 1.dp, color = HambugTheme.colors.primRed, shape = RoundedCornerShape(2.dp))
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "자유잡담",
                        style = HambugTheme.typography.label02,
                        color = HambugTheme.colors.primRed
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .height(34.dp)
                        .background(color = HambugTheme.colors.bgWhite)
                        .border(width = 1.dp, color = HambugTheme.colors.borderDisabled, shape = RoundedCornerShape(2.dp))
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "프렌차이즈",
                        style = HambugTheme.typography.label02,
                        color = HambugTheme.colors.textDisabled
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            RequiredFieldTitle(title = "제목")

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth( ),
                value = "",
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            RequiredFieldTitle(title = "내용")

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(234.dp)
                    .border(width = 1.dp, color = HambugTheme.colors.borderDefault, shape = RoundedCornerShape(6.dp)),
                value = "",
                onValueChange = {},
                label = { Text("자유롭게 이야기를 나눠보세요") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 이미지 표시 영역
            Row {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(color = HambugTheme.colors.bgDarker, shape = RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        modifier = Modifier.offset(x = 4.dp, y = (-10).dp),
                        imageVector = AppIcons.CircleCross,
                        contentDescription = null,
                        tint = HambugTheme.colors.iconDisabled
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier.offset(x = 4.dp, y = (-10).dp),
                        imageVector = AppIcons.CircleCross,
                        contentDescription = null,
                        tint = HambugTheme.colors.iconDisabled
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(color = HambugTheme.colors.bgDarker, shape = RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        modifier = Modifier.offset(x = 4.dp, y = (-10).dp),
                        imageVector = AppIcons.CircleCross,
                        contentDescription = null,
                        tint = HambugTheme.colors.iconDisabled
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier.offset(x = 4.dp, y = (-10).dp),
                        imageVector = AppIcons.CircleCross,
                        contentDescription = null,
                        tint = HambugTheme.colors.iconDisabled
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 사진추가 버튼
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = HambugTheme.colors.bgWhite)
                    .border(width = 1.dp, color = HambugTheme.colors.primRed),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = AppIcons.Camera,
                        contentDescription = null,
                        tint = HambugTheme.colors.primRed
                       )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "사진추가 (2/5)",
                        style = HambugTheme.typography.body03,
                        color = HambugTheme.colors.primRed
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 등록 버튼
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(color = HambugTheme.colors.primRed, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "등록",
                    style = HambugTheme.typography.title02,
                    color = HambugTheme.colors.bgWhite
                )
            }
        }
    }
}

@Preview
@Composable
fun PostWriteScreenPreview() {
    HambugTheme {
        PostWriteScreen()
    }
}
