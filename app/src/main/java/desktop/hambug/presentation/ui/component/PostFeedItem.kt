package desktop.hambug.presentation.ui.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import desktop.hambug.R
import desktop.hambug.presentation.ui.icon.AppIcons
import desktop.hambug.presentation.ui.icon.appicons.Comment
import desktop.hambug.presentation.ui.icon.appicons.Heart
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun PostFeedItem() {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.hambuger),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(192.dp)
                .clip(RoundedCornerShape(6.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
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
                    style = HambugTheme.typography.label02,
                    color = HambugTheme.colors.textDisabled
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "오늘 점심으로 맘스터치 싸이버거를 먹었는데, 역시 기대를 저버리지 않았어요. 일단 패티가 정말 두툼하고 겉은 바삭, 속은 촉촉해서 식감이 일품이에요. 특히 매콤달콤한 소스가 중독성이 강해서 먹는 내내 행복했어요. 신선한 양상추와 부드러운 빵까지 완벽한 조합이었습니다",
            style = HambugTheme.typography.label02,
            color = HambugTheme.colors.textDisabled,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
