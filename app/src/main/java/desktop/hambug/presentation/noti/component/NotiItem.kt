package desktop.hambug.presentation.noti.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import desktop.hambug.domain.model.Noti
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.presentation.util.getDisplayMessage
import desktop.hambug.presentation.util.toTimeAgoString

@Composable
fun NotiItem(
    noti: Noti,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = noti.createdAt.toTimeAgoString(),
                style = HambugTheme.typography.label02,
                color = HambugTheme.colors.textDisabled
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = noti.getDisplayMessage(),
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.textHeadline
            )
        }

        if (noti.thumbnailUrl.isNotEmpty()) {
            AsyncImage(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(44.dp)
                    .clip(RoundedCornerShape(5.dp)),
                model = noti.thumbnailUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

//        Box(
//            modifier = Modifier
//                .padding(start = 10.dp)
//                .size(44.dp)
//                .background(color = HambugTheme.colors.bgDarker, shape = RoundedCornerShape(5.dp)),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                modifier = Modifier.size(28.dp),
//                painter = painterResource(id = R.drawable.logo_login),
//                contentDescription = null,
//                colorFilter = ColorFilter.tint(HambugTheme.colors.borderDefault)
//            )
//        }
    }
}
