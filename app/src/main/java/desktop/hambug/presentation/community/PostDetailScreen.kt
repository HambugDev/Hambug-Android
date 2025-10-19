package desktop.hambug.presentation.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavHostController
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
fun PostDetailScreen(navController: NavHostController) {

    var showPostBottomSheet by remember { mutableStateOf(false) }
    var showCommentBottomSheet by remember { mutableStateOf(false) }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = HambugTheme.colors.bgWhite
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // 상단 프로필 영역
            PostDetailProfileSection(
                onClickBack = { navController.popBackStack() },
                onClickMore = { showPostBottomSheet = true }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 제목 + 시간 + 내용 영역
            PostDetailTextSection()

            Spacer(modifier = Modifier.height(20.dp))

            // 게시물 이미지 영역
            PostDetailImageSection()

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
                text = "닉네임",
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
fun PostDetailTextSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "맘스터치 싸이버거는 언제나 옳다! 겉바속촉 치킨 패티에 중독성 강한 소스가 대박!",
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
            text = "오늘 점심으로 맘스터치 싸이버거를 먹었는데, 역시 기대를 저버리지 않았어요. 일단 패티가 정말 두툼하고 겉은 바삭, 속은 촉촉해서 식감이 일품이에요. 특히 매콤달콤한 소스가 중독성이 강해서 먹는 내내 행복했어요. 신선한 양상추와 부드러운 빵까지 완벽한 조합이었습니다",
            style = HambugTheme.typography.body02,
            color = HambugTheme.colors.textHeadline
        )
    }
}

@Composable
fun PostDetailImageSection() {
    LazyRow(
        modifier = Modifier.padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(count = 5) {
            Image(
                modifier = Modifier
                    .size(272.dp)
                    .clip(RoundedCornerShape(6.dp)),
                painter = painterResource(R.drawable.hambuger),
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
