package desktop.hambug.presentation.community

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import desktop.hambug.domain.model.Comment
import desktop.hambug.presentation.community.component.CommentInputBar
import desktop.hambug.presentation.community.component.DetailMyBottomSheet
import desktop.hambug.presentation.community.component.DetailOtherBottomSheet
import desktop.hambug.presentation.ui.component.TwoButtonDialog
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.BackDetail
import desktop.hambug.presentation.ui.icon.appicons.CommentDetail
import desktop.hambug.presentation.ui.icon.appicons.Dots
import desktop.hambug.presentation.ui.icon.appicons.Heart
import desktop.hambug.presentation.ui.icon.appicons.HeartBorder
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.presentation.util.toTimeAgoString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardDetailScreen(
    navController: NavHostController,
    boardDetailViewModel: BoardDetailViewModel = hiltViewModel()
) {
    // CommunityScreen과 동일한 viewModelStoreOwner 사용
    val parentEntry = remember(navController.currentBackStackEntry) {
        navController.getBackStackEntry("main_graph")
    }
    val communityViewModel: CommunityViewModel = hiltViewModel(parentEntry)

    val uiState by boardDetailViewModel.uiState.collectAsStateWithLifecycle()
    val commentsState by boardDetailViewModel.commentsState.collectAsStateWithLifecycle()
    val isLikeProcessing by boardDetailViewModel.isLikeProcessing.collectAsStateWithLifecycle()
    val commentText by boardDetailViewModel.commentText.collectAsStateWithLifecycle()

    var showBoardBottomSheet by remember { mutableStateOf(false) }
    var showCommentBottomSheet by remember { mutableStateOf(false) }
    var showBoardRemoveDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding(),
        containerColor = HambugTheme.colors.bgWhite,
        bottomBar = {
            CommentInputBar(
                commentText = commentText,
                onTextChange = { boardDetailViewModel.onCommentTextChange(it) },
                onSubmit = { boardDetailViewModel.createComment() }
            )
        }
    ) { paddingValues ->

        when (uiState) {
            is BoardDetailUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = HambugTheme.colors.primRed,
                        strokeWidth = 4.dp
                    )
                }
            }
            is BoardDetailUiState.Error -> {}
            is BoardDetailUiState.Success -> {
                val board = (uiState as BoardDetailUiState.Success).board

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = 20.dp,
                        bottom = paddingValues.calculateBottomPadding()
                    )
                ) {
                    // 프로필 영역
                    item {
                        BoardDetailProfileSection(
                            authorNickname = board.authorNickname,
                            authorProfileImageUrl = board.authorProfileImageUrl,
                            onClickBack = { navController.popBackStack() },
                            onClickMore = { showBoardBottomSheet = true }
                        )
                        Spacer(Modifier.height(20.dp))
                    }

                    // 제목 + 시간 + 내용 영역
                    item {
                        BoardDetailTextSection(
                            title = board.title,
                            content = board.content,
                            createdAt = board.createdAt
                        )
                        Spacer(Modifier.height(20.dp))
                    }

                    // 게시물 이미지 영역
                    item {
                        if (board.imageUrls != null) {
                            BoardDetailImageSection(imageUrls = board.imageUrls)
                            Spacer(Modifier.height(20.dp))
                        } else {
                            Spacer(Modifier.height(40.dp))
                        }
                    }

                    // 아이콘 영역
                    item {
                        BoardDetailIconSection(
                            likeCount = board.likeCount,
                            commentCount = board.commentCount,
                            isLiked = board.isLiked,
                            isLikeProcessing = isLikeProcessing,
                            onLikeClick = { boardDetailViewModel.likeBoard() }
                        )
                        Spacer(Modifier.height(20.dp))
                    }

                    // 댓글 구분선
                    item {
                        HorizontalDivider(
                            thickness = 8.dp,
                            color = HambugTheme.colors.bgNormal
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    // 댓글 목록
                    when (commentsState) {
                        is CommentsUiState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 40.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(24.dp),
                                        color = HambugTheme.colors.primRed,
                                        strokeWidth = 2.dp
                                    )
                                }
                            }
                        }
                        is CommentsUiState.Error -> {}
                        is CommentsUiState.Success -> {
                            val comments = (commentsState as CommentsUiState.Success).comments

                            if (comments.isEmpty()) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 40.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "첫 댓글을 남겨보세요",
                                            style = HambugTheme.typography.body02,
                                            color = HambugTheme.colors.borderDefault
                                        )
                                    }
                                }
                            } else {
                                items(
                                    items = comments,
                                    key = { it.id }
                                ) { comment ->
                                    CommentItem(
                                        comment = comment,
                                        onClick = { showCommentBottomSheet = true }
                                    )
                                    Spacer(Modifier.height(20.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // 게시물 바텀시트
    if (showBoardBottomSheet) {
        val isAuthor = (uiState as BoardDetailUiState.Success).board.isAuthor

        ModalBottomSheet(
            onDismissRequest = { showBoardBottomSheet = false },
            dragHandle = null,
            containerColor = HambugTheme.colors.bgWhite,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            if (isAuthor) {
                // 내 게시물인 경우
                DetailMyBottomSheet(
                    onEdit = {},
                    onDelete = {
                        showBoardRemoveDialog = true
                        showBoardBottomSheet = false
                    },
                    onCancel = { showBoardBottomSheet = false }
                )
            } else {
                // 타인의 게시물인 경우
                DetailOtherBottomSheet(
                    onReport = {},
                    onCancel = { showBoardBottomSheet = false }
                )
            }
        }
    }

    // 댓글 바텀시트
    if (showCommentBottomSheet) {
        // 자신의 댓글

        // 타인의 댓글
        ModalBottomSheet(
            onDismissRequest = { showCommentBottomSheet = false },
            dragHandle = null,
            containerColor = HambugTheme.colors.bgWhite,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            DetailOtherBottomSheet(
                onReport = {},
                onCancel = { showCommentBottomSheet = false }
            )
        }
    }

    // 게시물 삭제 모달
    if (showBoardRemoveDialog) {
        TwoButtonDialog(
            title = "게시물을 삭제하시겠어요?",
            content = null,
            onDismiss = { showBoardRemoveDialog = false },
            onCancel = { showBoardRemoveDialog = false },
            onConfirm = {
                boardDetailViewModel.deleteBoard(
                    onSuccess = {
                        communityViewModel.resetAllCache()

                        showBoardRemoveDialog = false
                        navController.popBackStack()
                    }
                )
            }
        )
    }
}

@Composable
fun BoardDetailProfileSection(
    authorNickname: String,
    authorProfileImageUrl: String,
    onClickBack: () -> Unit,
    onClickMore: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .clickable { onClickBack() }
                    .padding(top = 10.dp, bottom = 10.dp, end = 16.dp),
                imageVector = AppIcons.BackDetail,
                contentDescription = null,
                tint = HambugTheme.colors.iconDefault
            )
            AsyncImage(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape),
                model = authorProfileImageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = authorNickname,
                style = HambugTheme.typography.body02,
                color = HambugTheme.colors.textHeadline
            )
        }

        Icon(
            modifier = Modifier.clickable { onClickMore() },
            imageVector = AppIcons.Dots,
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Composable
fun BoardDetailTextSection(
    title: String,
    content: String,
    createdAt: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            style = HambugTheme.typography.title02,
            color = HambugTheme.colors.textHeadline
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = createdAt.toTimeAgoString(),
            style = HambugTheme.typography.label02,
            color = HambugTheme.colors.textDisabled
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = content,
            style = HambugTheme.typography.body02,
            color = HambugTheme.colors.textHeadline
        )
    }
}

@Composable
fun BoardDetailImageSection(
    imageUrls: List<String>
) {
    if (imageUrls.size == 1) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(6.dp)),
                model = imageUrls[0],
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    } else {
        LazyRow(
            modifier = Modifier.padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(imageUrls) { imageUrl ->
                AsyncImage(
                    modifier = Modifier
                        .size(272.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun BoardDetailIconSection(
    likeCount: Int,
    commentCount: Int,
    isLiked: Boolean,
    isLikeProcessing: Boolean,
    onLikeClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .clickable(
                        // 중복 클릭 방지
                        enabled = !isLikeProcessing,
                        onClick = onLikeClick
                    )
                    .size(20.dp),
                imageVector = if (isLiked) AppIcons.Heart else AppIcons.HeartBorder,
                contentDescription = null,
                tint = HambugTheme.colors.primRed
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = likeCount.toString(),
                style = HambugTheme.typography.body01,
                color = HambugTheme.colors.textDisabled
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = AppIcons.CommentDetail,
                contentDescription = null,
                tint = HambugTheme.colors.iconDisabled
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = commentCount.toString(),
                style = HambugTheme.typography.body01,
                color = HambugTheme.colors.textDisabled
            )
        }
    }
}

@Composable
fun CommentItem(
    comment: Comment,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape),
            model = comment.authorProfileImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = comment.authorNickname,
                    style = HambugTheme.typography.body02,
                    color = HambugTheme.colors.textHeadline
                )
                Icon(
                    modifier = Modifier.clickable { onClick() },
                    imageVector = AppIcons.Dots,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            Text(
                text = "15분 전",
                style = HambugTheme.typography.label02,
                color = HambugTheme.colors.textDisabled
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = comment.content,
                style = HambugTheme.typography.body03,
                color = HambugTheme.colors.textHeadline
            )
        }
    }
}

@Preview
@Composable
fun BoardDetailScreenPreview() {
    HambugTheme {
//        BoardDetailScreen()
    }
}
