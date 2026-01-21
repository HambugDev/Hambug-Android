package desktop.hambug.presentation.community.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import desktop.hambug.domain.model.Board
import desktop.hambug.presentation.component.HambugLoadingIndicator
import desktop.hambug.presentation.component.IndicatorSize
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun ListViewContent(
    boards: List<Board>,
    scrollState: LazyListState,
    onClick: (Int) -> Unit,
    onLoadMore: () -> Unit,
    isLoadingMore: Boolean
) {
    // 스크롤 하단 감지하여 다음 페이지 로드
    LaunchedEffect(Unit) {
        snapshotFlow {
            val totalItems = scrollState.layoutInfo.totalItemsCount
            val lastVisibleItem = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            // 스크롤이 하단 3개 아이템에 도달했는지 체크
            totalItems > 5 && lastVisibleItem >= totalItems - 3
        }
            .distinctUntilChanged()    // 값이 변경될 때만
            .filter { it }             // ture일 때만
            .collect { onLoadMore() }
    }

    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        state = scrollState,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(
            items = boards,
            key = { it.id }
        ) { board ->
            PostListItem(
                board = board,
                onClick = { onClick(board.id) }
            )
        }

        // 하단 로딩 인디케이터
        if (isLoadingMore) {
            item(key = "indicator") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    HambugLoadingIndicator(indicatorSize = IndicatorSize.Small)
                }
            }
        }
    }
}
