package desktop.hambug.presentation.community.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import desktop.hambug.R
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Comment
import desktop.hambug.presentation.ui.icon.appicons.Heart
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun PostListItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "맘스터치 싸이버거는 언제나 옳다! 겉바속촉 치킨 패티에 중독성 강한 소스가 대박!",
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

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "패티포터",
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
            }
        }

        Image(
            painter = painterResource(R.drawable.hambuger),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(5.dp))
        )
    }
}
