package desktop.hambug.presentation.home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import desktop.hambug.R
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Bell
import desktop.hambug.presentation.ui.icon.appicons.Comment
import desktop.hambug.presentation.ui.icon.appicons.Hambug
import desktop.hambug.presentation.ui.icon.appicons.Heart
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val albums by viewModel.albums.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getAlbums(userId = 1)
    }

    Scaffold(
        containerColor = HambugTheme.colors.bgNormal,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = HambugTheme.colors.bgNormal
                ),
                navigationIcon = {
                    HomeNavigationIcon()
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .clickable { navController.navigate("bell") }
                            .padding(16.dp),
                        imageVector = AppIcons.Bell,
                        contentDescription = null,
                        tint = HambugTheme.colors.iconDefault
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(Modifier.height(12.dp))

            // 추천버거 영역
            RecommendBurgerSection()

            Spacer(Modifier.height(30.dp))

            // 인기글 영역
            HomePostSection()

            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun HomeNavigationIcon() {
    Row(
        modifier = Modifier.padding(start = 16.dp),
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
fun RecommendBurgerSection() {
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
            items(count = 3) {
                // 추천버거 아이템
                RecommendBurgerItem()
            }
        }
    }
}

@Composable
fun RecommendBurgerItem() {
    Column(
        modifier = Modifier
            .width(260.dp)
            .background(color = HambugTheme.colors.bgWhite, shape = RoundedCornerShape(16.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(180.dp),
            painter = painterResource(R.drawable.burger),
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
                text = "버거킹",
                style = HambugTheme.typography.body04Prominent,
                color = HambugTheme.colors.bgWhite
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "베이컨토마토디럭스",
            style = HambugTheme.typography.title02,
            color = HambugTheme.colors.textHeadline
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "두툼한 비프, 1955소스의 오리지널 풍미",
            style = HambugTheme.typography.body03,
            color = HambugTheme.colors.textDisabled,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HomePostSection() {
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
            for (i in 0 until 5) {
                HomePostItem()

                if (i < 4) {
                    HorizontalDivider(thickness = 0.5.dp, color = HambugTheme.colors.bgNormal)
                }
            }
        }
    }
}

@Composable
fun HomePostItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "다들 햄최몇인가요 다들 햄최몇인가요 다들 햄최몇인가요",
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.textBody,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "저 오늘 버거킹가서 햄버거 세트 2개 먹었는데!! 진짜 맛있었어요 저 오늘 버거킹가서 햄버거 세트 2개 먹었는데!! 진짜 맛있었어요",
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
                    text = "게시판",
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
                        text = "11",
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
                        text = "6",
                        style = HambugTheme.typography.body03,
                        color = HambugTheme.colors.textBody
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "5분 전",
                    style = HambugTheme.typography.body04Prominent,
                    color = HambugTheme.colors.textDisabled
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(90.dp)
                .background(color = HambugTheme.colors.bgYellow, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp),
                painter = painterResource(R.drawable.burger),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
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
