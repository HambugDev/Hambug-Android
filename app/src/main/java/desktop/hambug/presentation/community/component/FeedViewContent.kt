package desktop.hambug.presentation.community.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import desktop.hambug.domain.model.Board
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun FeedViewContent(
    boards: List<Board>,
    scrollState: LazyListState,
    onClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = scrollState
    ) {
        item {
            Spacer(Modifier.height(20.dp))
        }

        itemsIndexed(boards) { idx, board ->
            if (idx > 0) {
                Spacer(Modifier.height(16.dp))
            }
            PostFeedItem(
                board = board,
                onClick = { onClick() }
            )
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(thickness = 1.dp, color = HambugTheme.colors.bgDarker)
        }
    }
}
