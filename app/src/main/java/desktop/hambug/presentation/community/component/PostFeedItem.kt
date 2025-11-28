package desktop.hambug.presentation.community.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun PostFeedItem(
    board: Board,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        if (board.imageUrl != null) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)
                    .clip(RoundedCornerShape(6.dp)),
                model = board.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,

            )
            Spacer(Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = board.title,
                modifier = Modifier.weight(1f),
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.textBody,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "2분 전",
                modifier = Modifier.padding(start = 10.dp),
                style = HambugTheme.typography.label02,
                color = HambugTheme.colors.textDisabled
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = board.authorNickname,
                style = HambugTheme.typography.label02,
                color = HambugTheme.colors.textBody
            )

            Spacer(Modifier.width(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = AppIcons.Heart,
                    contentDescription = null,
                    tint = HambugTheme.colors.primRed
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = board.likeCount.toString(),
                    style = HambugTheme.typography.label02,
                    color = HambugTheme.colors.textDisabled
                )
            }

            Spacer(Modifier.width(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = AppIcons.Comment,
                    contentDescription = null,
                    tint = HambugTheme.colors.iconDisabled
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "6",
                    style = HambugTheme.typography.label02,
                    color = HambugTheme.colors.textDisabled
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = board.content,
            style = HambugTheme.typography.label02,
            color = HambugTheme.colors.textDisabled,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
