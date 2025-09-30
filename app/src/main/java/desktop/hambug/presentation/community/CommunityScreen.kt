package desktop.hambug.presentation.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.community.component.PostFeedItem
import desktop.hambug.presentation.ui.theme.CommunityFilterSelected
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen() {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "커뮤니티",
                        style = HambugTheme.typography.title02,
                        color = HambugTheme.colors.primWhite
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = HambugTheme.colors.primRed
                ),
                actions = {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = HambugTheme.colors.bgNormal)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .background(color = HambugTheme.colors.primRed)
                    .padding(horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .height(34.dp)
                            .background(color = CommunityFilterSelected, shape = RoundedCornerShape(6.dp))
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "전체",
                            style = HambugTheme.typography.label02,
                            color = HambugTheme.colors.primRed
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .height(34.dp)
                            .background(color = HambugTheme.colors.primWhite, shape = RoundedCornerShape(6.dp))
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "자유잡담",
                            style = HambugTheme.typography.label02,
                            color = HambugTheme.colors.textDisabled
                        )
                    }
                }
            }

            // 리스트형
//            Box(
//                modifier = Modifier
//                    .padding(horizontal = 20.dp)
//                    .fillMaxSize()
//            ) {
//                Column (
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .offset(y = 46.dp)
//                        .background(color = Color.White, shape = RoundedCornerShape(6.dp))
//                        .padding(horizontal = 20.dp)
//                ) {
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    for (i in 0 until 10) {
//                        PostListItem()
//                        Spacer(modifier = Modifier.height(24.dp))
//                    }
//                }
//            }

            // 피드형
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 46.dp)
            ) {
                for (idx in 0 until 3) {
                    if (idx > 0) {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    PostFeedItem()
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(thickness = 2.dp, color = HambugTheme.colors.borderDisabled)
                }
            }
        }
    }
}

@Preview
@Composable
fun CommunityScreenPreview() {
    HambugTheme {
        CommunityScreen()
    }
}
