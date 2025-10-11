package desktop.hambug.presentation.my

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.community.component.PostListItem
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.BackDetail
import desktop.hambug.presentation.ui.icon.appicons.CommentBorder
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun MyActivityScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = HambugTheme.colors.bgNormal
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.height(16.dp))

            MyActivityHeaderSection(
                onClick = {}
            )

            Spacer(Modifier.height(8.dp))

            // 탭 영역 (게시물 / 댓글)
            TwoTabSection()
        }
    }
}

@Composable
fun MyActivityHeaderSection(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(8.dp),
            imageVector = AppIcons.BackDetail,
            contentDescription = null,
            tint = HambugTheme.colors.iconDefault
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "활동내역",
            style = HambugTheme.typography.title02,
            color = HambugTheme.colors.textHeadline
        )
    }
}

@Composable
fun TwoTabSection() {
    val tabs = listOf("게시물", "댓글")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = HambugTheme.colors.bgNormal,
            indicator = { tabPositions ->
                // 커스텀 인디케이터 (활성화된 탭의 아래 선)
                TabRowDefaults.SecondaryIndicator(
                    // 현재 선택된 탭 위치로 인디케이터를 이동시킴
                    modifier = Modifier.tabIndicatorOffset(currentTabPosition = tabPositions[selectedTabIndex]),
                    color = HambugTheme.colors.primRed,
                    height = 1.5.dp
                )
            },
            divider = {
                HorizontalDivider(thickness = 1.5.dp, color = HambugTheme.colors.borderDefault)
            }
        ) {
            tabs.forEachIndexed { idx, title ->
                Tab(
                    selected = selectedTabIndex == idx,
                    onClick = { selectedTabIndex = idx },
                    text = {
                        val color = if (selectedTabIndex == idx) HambugTheme.colors.primRed else HambugTheme.colors.textDisabled
                        Text(
                            text = title,
                            style = HambugTheme.typography.body02,
                            color = color
                        )
                    },
                    selectedContentColor = Color.White
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // 탭 콘텐츠 (개시물 / 댓글)
        when (selectedTabIndex) {
            0 -> {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxSize()
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = HambugTheme.colors.bgWhite, shape = RoundedCornerShape(6.dp))
                            .padding(horizontal = 20.dp)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        for (i in 0 until 10) {
                            PostListItem(
                                onClick = {}
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
            1 -> {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxSize()
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = HambugTheme.colors.bgWhite, shape = RoundedCornerShape(6.dp))
                            .padding(horizontal = 20.dp)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        for (i in 0 until 10) {
                            MyActivityCommentItem()
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyActivityCommentItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "맘스터치 싸이버거는 언제나 옳다! 겉바속촉 치킨 패티에 중독성 강한 소스가 대박!",
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.textBody,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "2분 전",
                modifier = Modifier.padding(start = 10.dp),
                style = HambugTheme.typography.label02,
                color = HambugTheme.colors.textDisabled
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = AppIcons.CommentBorder,
                contentDescription = null,
                tint = HambugTheme.colors.iconDisabled
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "내가 작성한 댓글 내가 작성한 댓글 내가 작성한 댓글 내가 작성한 댓글 ",
                style = HambugTheme.typography.body03,
                color = HambugTheme.colors.textHeadline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun MyActivityScreenPreview() {
    HambugTheme {
        MyActivityScreen()
    }
}
