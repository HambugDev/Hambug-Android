package desktop.hambug.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import desktop.hambug.domain.model.HomeBoard
import desktop.hambug.domain.model.HomeBurger
import desktop.hambug.presentation.component.HambugLoadingIndicator
import desktop.hambug.presentation.component.topbar.MainTopBar
import desktop.hambug.presentation.component.topbar.NotiIcon
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Comment
import desktop.hambug.presentation.ui.icon.appicons.Hambug
import desktop.hambug.presentation.ui.icon.appicons.Heart
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.presentation.util.toKoreanCategory
import desktop.hambug.presentation.util.toTimeAgoString

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(
        viewModelStoreOwner = navController.getBackStackEntry("main_graph")
    )
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = HambugTheme.colors.bgNormal,
        topBar = {
            MainTopBar(
                title = { HomeTopbarLogo() },
                actions = {
                    NotiIcon(
                        onClick = { navController.navigate("bell") },
                        tint = HambugTheme.colors.iconDefault
                    )
                },
                containerColor = HambugTheme.colors.bgNormal
            )
        }
    ) { paddingValues ->

        when (uiState) {
            is HomeUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    HambugLoadingIndicator()
                }
            }
            is HomeUiState.Error -> {

            }
            is HomeUiState.Success -> {
                val data = uiState as HomeUiState.Success

                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))

                    // 추천버거 영역
                    RecommendBurgerSection(data.burgers)

                    Spacer(Modifier.height(30.dp))

                    // 인기글 영역
                    HomeBoardSection(
                        boards = data.boards,
                        onClick = { boardId ->
                            navController.navigate("community_detail/$boardId")
                        }
                    )

                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun HomeTopbarLogo() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = AppIcons.Hambug,
            contentDescription = null,
            tint = HambugTheme.colors.primRed
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = "햄버그",
            style = HambugTheme.typography.heading04,
            color = HambugTheme.colors.primRed
        )
    }
}

@Composable
fun RecommendBurgerSection(
    burgers: List<HomeBurger>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        Text(
            text = "오늘의 추천 버거",
            style = HambugTheme.typography.title02,
            color = HambugTheme.colors.textBody
        )

        Spacer(Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(burgers) { burger ->
                RecommendBurgerItem(
                    burger = burger
                )
            }
        }
    }
}

@Composable
fun RecommendBurgerItem(
    burger: HomeBurger
) {
    Column(
        modifier = Modifier
            .width(260.dp)
            .background(color = HambugTheme.colors.bgWhite, shape = RoundedCornerShape(16.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.size(180.dp),
            model = burger.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .background(color = HambugTheme.colors.primRed, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = burger.franchiseName,
                style = HambugTheme.typography.body04Prominent,
                color = HambugTheme.colors.bgWhite
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = burger.name,
            style = HambugTheme.typography.title02,
            color = HambugTheme.colors.textHeadline
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = burger.description,
            style = HambugTheme.typography.body03,
            color = HambugTheme.colors.textDisabled,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HomeBoardSection(
    boards: List<HomeBoard>,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "인기글",
            style = HambugTheme.typography.title02,
            color = HambugTheme.colors.textBody
        )

        Spacer(Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = HambugTheme.colors.bgWhite, shape = RoundedCornerShape(16.dp))
        ) {
            boards.forEachIndexed { idx, board ->
                HomePostItem(
                    board = board,
                    onClick = { onClick(board.id) }
                )

                if (idx < 4) {
                    HorizontalDivider(thickness = 0.5.dp, color = HambugTheme.colors.bgNormal)
                }
            }
        }
    }
}

@Composable
fun HomePostItem(
    board: HomeBoard,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = board.title,
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.textBody,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = board.content,
                style = HambugTheme.typography.body03,
                color = HambugTheme.colors.textDisabled,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = board.category.toKoreanCategory(),
                    style = HambugTheme.typography.body04Prominent,
                    color = HambugTheme.colors.primRed
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

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = board.createdAt.toTimeAgoString(),
                    style = HambugTheme.typography.body04Prominent,
                    color = HambugTheme.colors.textDisabled
                )
            }
        }

        if (board.imageUrl != null) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(90.dp)
                    .background(color = HambugTheme.colors.bgYellow, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    model = board.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HambugTheme {
//        HomeScreen()
    }
}
