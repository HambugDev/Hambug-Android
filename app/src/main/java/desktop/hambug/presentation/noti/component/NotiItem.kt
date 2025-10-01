package desktop.hambug.presentation.noti.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import desktop.hambug.R
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun NotiItem(
    content: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.hambuger),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(5.dp))
        )

        Spacer(Modifier.width(10.dp))

        Column {
            Text(
                text = "1시간 전",
                style = HambugTheme.typography.label02,
                color = HambugTheme.colors.textDisabled
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = content,
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.textHeadline
            )
        }
    }
}
