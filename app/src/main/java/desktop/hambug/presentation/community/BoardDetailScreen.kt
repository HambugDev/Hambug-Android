package desktop.hambug.presentation.community

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import desktop.hambug.R
import desktop.hambug.presentation.community.component.DetailMyBottomSheet
import desktop.hambug.presentation.community.component.DetailOtherBottomSheet
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.BackDetail
import desktop.hambug.presentation.ui.icon.appicons.CommentDetail
import desktop.hambug.presentation.ui.icon.appicons.Dots
import desktop.hambug.presentation.ui.icon.appicons.HeartBorder
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardDetailScreen(
    navController: NavHostController,
    boardDetailViewModel: BoardDetailViewModel = hiltViewModel()
) {
    val uiState by boardDetailViewModel.uiState.collectAsStateWithLifecycle()

    var showPostBottomSheet by remember { mutableStateOf(false) }
    var showCommentBottomSheet by remember { mutableStateOf(false) }

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = HambugTheme.colors.bgWhite
    ) {
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
                val data = uiState as BoardDetailUiState.Success

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    // 상단 프로필 영역
                    PostDetailProfileSection(
                        authorNickname = data.board.authorNickname,
                        onClickBack = { navController.popBackStack() },
                        onClickMore = { showPostBottomSheet = true }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // 제목 + 시간 + 내용 영역
                    PostDetailTextSection(
                        title = data.board.title,
                        content = data.board.content
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (data.board.imageUrls != null) {
                        // 게시물 이미지 영역
                        PostDetailImageSection(
                            imageUrls = data.board.imageUrls
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // 아이콘 영역
                    PostDetailIconSection()

                    Spacer(modifier = Modifier.height(36.dp))

                    // 댓글 영역
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    ) {
                        Image(
                            modifier = Modifier.size(35.dp),
                            painter = painterResource(id = R.drawable.logo_profile),
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "상하이버거상하이버거",
                                    style = HambugTheme.typography.body02,
                                    color = HambugTheme.colors.textHeadline
                                )
                                Icon(
                                    modifier = Modifier.clickable { showCommentBottomSheet = true },
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
                                text = "와 이 햄버거 진짜 맛있어 보이네요! 패티가 두툼하고 야채 신선해 보여요. 저도 꼭 가봐야겠어요. 혹시 소스는 어떤 맛인가요? 치즈도 듬뿍 들어있는 게 정말 좋아 보입니다. 빵도 촉촉해 보이고 구성이 완벽하네요. 다음 주말에 방문 예정인데 너무 기대되요! 가격대는 어느 정도인가요?",
                                style = HambugTheme.typography.body03,
                                color = HambugTheme.colors.textHeadline
                            )
                        }
                    }
                }
            }
        }
    }

    // 게시물 바텀시트
    if (showPostBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showPostBottomSheet = false },
            dragHandle = null,
            containerColor = HambugTheme.colors.bgWhite,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            // 자신의 게시물
            DetailMyBottomSheet(
                onEdit = {},
                onDelete = {},
                onCancel = { showPostBottomSheet = false }
            )
            // 타인의 게시물
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
}

@Composable
fun PostDetailProfileSection(
    authorNickname: String,
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
            Image(
                modifier = Modifier.size(35.dp),
                painter = painterResource(id = R.drawable.logo_profile),
                contentDescription = null
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
fun PostDetailTextSection(
    title: String,
    content: String
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
            text = "15분 전",
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
fun PostDetailImageSection(
    imageUrls: List<String>
) {
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

@Composable
fun PostDetailIconSection() {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = AppIcons.HeartBorder,
                contentDescription = null,
                tint = HambugTheme.colors.primRed
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "11",
                style = HambugTheme.typography.body01,
                color = HambugTheme.colors.textDisabled
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = AppIcons.CommentDetail,
                contentDescription = null,
                tint = HambugTheme.colors.iconDisabled
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "6",
                style = HambugTheme.typography.body01,
                color = HambugTheme.colors.textDisabled
            )
        }
    }
}

@Preview
@Composable
fun PostDetailScreenPreview() {
    HambugTheme {
//        PostDetailScreen()
    }
}
