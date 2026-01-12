package desktop.hambug.presentation.my

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import desktop.hambug.domain.model.MyBoard
import desktop.hambug.domain.model.MyComment
import desktop.hambug.presentation.ui.component.HambugLoadingIndicator
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.BackDetail
import desktop.hambug.presentation.ui.icon.appicons.Comment
import desktop.hambug.presentation.ui.icon.appicons.CommentBorder
import desktop.hambug.presentation.ui.icon.appicons.Heart
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.presentation.util.toTimeAgoString

@Composable
fun MyActivityScreen(
    navController: NavHostController,
    myActivityViewModel: MyActivityViewModel = hiltViewModel()
) {
    val uiState by myActivityViewModel.uiState.collectAsStateWithLifecycle()
    val commentsState by myActivityViewModel.commentsSate.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = HambugTheme.colors.bgNormal
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.height(16.dp))

            MyActivityHeaderSection(
                onClick = { navController.popBackStack() }
            )

            Spacer(Modifier.height(8.dp))

            // 탭 영역 (게시물 / 댓글)
            TwoTabSection(
                uiState = uiState,
                commentsState = commentsState,
                onClick = { boardId ->
                    navController.navigate("community_detail/$boardId")
                }
            )
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
fun TwoTabSection(
    uiState: MyActivityUiState,
    commentsState: MyCommentUiState,
    onClick: (Int) -> Unit
) {
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

        // 콘텐츠 영역 (개시물 / 댓글)
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            when (selectedTabIndex) {
                0 -> {
                    when (uiState) {
                        is MyActivityUiState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                HambugLoadingIndicator()
                            }
                        }
                        is MyActivityUiState.Error -> {}
                        is MyActivityUiState.Success -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = HambugTheme.colors.bgWhite, shape = RoundedCornerShape(6.dp)),
                                state = rememberLazyListState(),
                                contentPadding = PaddingValues(20.dp),
                                verticalArrangement = Arrangement.spacedBy(24.dp)
                            ) {
                                items(
                                    items = uiState.boards,
                                    key = { it.id }
                                ) { board ->
                                    MyBoardItem(
                                        board = board,
                                        onClick = { onClick(board.id) }
                                    )
                                }
                            }
                        }
                    }
                }
                1 -> {
                    when (commentsState) {
                        is MyCommentUiState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                HambugLoadingIndicator()
                            }
                        }
                        is MyCommentUiState.Error -> {}
                        is MyCommentUiState.Success -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = HambugTheme.colors.bgWhite, shape = RoundedCornerShape(6.dp)),
                                state = rememberLazyListState(),
                                contentPadding = PaddingValues(20.dp),
                                verticalArrangement = Arrangement.spacedBy(24.dp)
                            ) {
                                items(
                                    items = commentsState.comments,
                                    key = { it.commentId }
                                ) { comment ->
                                    MyCommentItem(
                                        comment = comment,
                                        onClick = { onClick(comment.boardId) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyBoardItem(
    board: MyBoard,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    // 제목이 짧은 경우 제목 옆에 시간을 붙이고, 제목이 긴 경우 말줄임 처리
                    modifier = Modifier.weight(1f, fill = false),
                    text = board.title,
                    style = HambugTheme.typography.body02Prominent,
                    color = HambugTheme.colors.textBody,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = board.createdAt.toTimeAgoString(),
                    style = HambugTheme.typography.label02,
                    color = HambugTheme.colors.textDisabled
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = board.authorNickname,
                    style = HambugTheme.typography.label02,
                    color = HambugTheme.colors.textBody
                )

                Spacer(modifier = Modifier.width(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = AppIcons.Heart,
                        contentDescription = null,
                        tint = HambugTheme.colors.primRed
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = board.likeCount.toString(),
                        style = HambugTheme.typography.label02,
                        color = HambugTheme.colors.textDisabled
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = AppIcons.Comment,
                        contentDescription = null,
                        tint = HambugTheme.colors.iconDisabled
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = board.commentCount.toString(),
                        style = HambugTheme.typography.body03,
                        color = HambugTheme.colors.textBody
                    )
                }
            }
        }

        if (board.imageUrl != null) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(50.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = HambugTheme.colors.bgYellow)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    model = board.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Composable
fun MyCommentItem(
    comment: MyComment,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = comment.boardTitle,
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.textBody,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = comment.createdAt.toTimeAgoString(),
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
                text = comment.commentContent,
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
//        MyActivityScreen()
    }
}
