package desktop.hambug.presentation.community

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import desktop.hambug.domain.model.Filter
import desktop.hambug.domain.model.FilterType
import desktop.hambug.presentation.community.component.CustomFloatingActionButton
import desktop.hambug.presentation.community.component.FeedViewContent
import desktop.hambug.presentation.community.component.ListViewContent
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.BellBorder
import desktop.hambug.presentation.ui.theme.CommunityFilterSelected
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(
    navController: NavHostController,
    communityViewModel: CommunityViewModel = hiltViewModel(
        viewModelStoreOwner = navController.getBackStackEntry("main_graph")
    )
) {
    val uiState by communityViewModel.currentUiState.collectAsStateWithLifecycle()

    // 필터링 목록 (전체, 자유잡담, 햄버거리뷰, 맛집추천)
    val filterList = communityViewModel.filterList
    // 리스트형 여부 (리스트형 or 피드형)
    val isListView by communityViewModel.isListView.collectAsStateWithLifecycle()
    // 현재 선택된 필터
    val currentFilter by communityViewModel.currentFilter.collectAsStateWithLifecycle()
    val isLoadingMore by communityViewModel.isLoadingMore.collectAsStateWithLifecycle()

    // 현재 선택된 필터(탭)에 해당하는 스크롤 상태를 가져오거나 생성한다
    // -> 탭 전환 시 스크롤 위치를 복원/유지하기 위함
    val scrollState = remember(currentFilter) {
        communityViewModel.getScrollPositionForFilter(currentFilter)
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
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
                        modifier = Modifier
                            .clickable { navController.navigate("bell") }
                            .padding(8.dp)
                            .size(32.dp),
                        imageVector = AppIcons.BellBorder,
                        contentDescription = null,
                        tint = HambugTheme.colors.bgWhite
                    )
                }
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton(
                onClick = {
                    navController.navigate("write")
                }
            )
        }
    ) { paddingValues ->

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = HambugTheme.colors.bgNormal)
        ) {
            // paddingValues 적용 후의 사용 가능 높이
            val availableHeight = maxHeight

            // 필터 영역
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .background(color = HambugTheme.colors.primRed)
                    .padding(horizontal = 20.dp)
            ) {
                FilterButtonSection(
                    filterList = filterList,
                    currentFilter = currentFilter,
                    onClick = { filterType -> communityViewModel.setFilter(filterType) }
                )
            }

            // 콘텐츠 영역
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(availableHeight - 46.dp)
                    .offset(y = 46.dp)
                    .background(color = HambugTheme.colors.bgWhite, shape = RoundedCornerShape(6.dp))
            ) {
                when (uiState) {
                    is CommunityUiState.Loading -> {
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
                    is CommunityUiState.Error -> {}
                    is CommunityUiState.Success -> {
                        val data = uiState as CommunityUiState.Success

                        if (isListView) {
                            // 리스트형 (전체, 자유잡담)
                            ListViewContent(
                                boards = data.boards,
                                scrollState = scrollState,
                                onClick = { boardId ->
                                    navController.navigate("community_detail/$boardId")
                                },
                                onLoadMore = { communityViewModel.loadMoreBoards() },
                                isLoadingMore = isLoadingMore
                            )
                        } else {
                            // 피드형 (햄버거리뷰, 맛집추천)
                            FeedViewContent(
                                boards = data.boards,
                                scrollState = scrollState,
                                onClick = { boardId ->
                                    navController.navigate("community_detail/$boardId")
                                },
                                onLoadMore = { communityViewModel.loadMoreBoards() },
                                isLoadingMore = isLoadingMore
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButtonSection(
    filterList: List<Filter>,
    currentFilter: FilterType,
    onClick: (FilterType) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        filterList.forEach { item ->
            val selected = currentFilter == item.type

            FilterButtonItem(
                filter = item,
                selected = selected,
                onClick = onClick
            )
            Spacer(Modifier.width(8.dp))
        }
    }
}

@Composable
fun FilterButtonItem(
    filter: Filter,
    selected: Boolean,
    onClick: (FilterType) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClick(filter.type) }
            .height(34.dp)
            .background(
                color = if (selected) CommunityFilterSelected else HambugTheme.colors.primWhite,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = filter.title,
            style = HambugTheme.typography.label02,
            color = if (selected) HambugTheme.colors.primRed else HambugTheme.colors.textDisabled
        )
    }
}

@Preview
@Composable
fun CommunityScreenPreview() {
    HambugTheme {
//        CommunityScreen()
    }
}
