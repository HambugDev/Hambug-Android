package desktop.hambug.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun ListViewContent(
    scrollState: LazyListState,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 46.dp)
                .background(color = HambugTheme.colors.bgWhite, shape = RoundedCornerShape(6.dp))
                .padding(horizontal = 20.dp),
            // 스크롤 상태 연결
            state = scrollState
        ) {
            item {
                Spacer(Modifier.height(20.dp))
            }

            items(30) {
                PostListItem(
                    onClick = { onClick() }
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
