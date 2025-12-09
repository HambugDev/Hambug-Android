package desktop.hambug.presentation.community.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import desktop.hambug.domain.model.Board

@Composable
fun ListViewContent(
    boards: List<Board>,
    scrollState: LazyListState,
    onClick: (Int) -> Unit
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        // 스크롤 상태 연결
        state = scrollState
    ) {
        item {
            Spacer(Modifier.height(20.dp))
        }

        itemsIndexed(boards) { idx, board ->
            if (idx > 0) {
                Spacer(Modifier.height(16.dp))
            }
            PostListItem(
                board = board,
                onClick = { onClick(board.id) }
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
