package desktop.hambug.presentation.community.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import desktop.hambug.domain.model.Board
import desktop.hambug.presentation.ui.theme.HambugTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun FeedViewContent(
    boards: List<Board>,
    scrollState: LazyListState,
    onClick: (Int) -> Unit,
    onLoadMore: () -> Unit,
    isLoadingMore: Boolean
) {
    LaunchedEffect(Unit) {
        snapshotFlow {
            val totalItems = scrollState.layoutInfo.totalItemsCount
            val lastVisibleItem = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            // 스크롤이 하단 3개 아이템에 도달했는지 체크
            totalItems > 5 && lastVisibleItem >= totalItems - 3
        }
            .distinctUntilChanged()
            .filter { it }
            .collect { onLoadMore() }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = scrollState,
        contentPadding = PaddingValues(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = boards,
            key = { it.id }
        ) { board ->
            PostFeedItem(
                board = board,
                onClick = { onClick(board.id) }
            )
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(thickness = 1.dp, color = HambugTheme.colors.bgDarker)
        }

        if (isLoadingMore) {
            item(key = "indicator") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
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
    }
}
