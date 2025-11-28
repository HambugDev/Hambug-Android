package desktop.hambug.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import desktop.hambug.domain.model.Board
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Comment
import desktop.hambug.presentation.ui.icon.appicons.Heart
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun PostListItem(
    board: Board,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    // 제목이 짧은 경우 제목 옆에 시간을 붙이고, 제목이 긴 경우 말줄임 처리
                    modifier = Modifier.weight(1f, fill = false),
                    text = board.title,
                    style = HambugTheme.typography.body02Prominent,
                    color = HambugTheme.colors.textBody,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "2분 전",
                    style = HambugTheme.typography.label02,
                    color = HambugTheme.colors.textDisabled
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = board.authorNickname,
                    style = HambugTheme.typography.label02,
                    color = HambugTheme.colors.textBody
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
                        text = "6",
                        style = HambugTheme.typography.body03,
                        color = HambugTheme.colors.textBody
                    )
                }
            }
        }

        if (board.imageUrl != null) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(50.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = HambugTheme.colors.bgYellow)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    model = board.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}
