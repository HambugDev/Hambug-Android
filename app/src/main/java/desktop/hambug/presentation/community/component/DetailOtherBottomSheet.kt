package desktop.hambug.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun DetailOtherBottomSheet(
    onReport: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable { onReport() }
                .fillMaxWidth()
                .background(color = HambugTheme.colors.bgNormal, RoundedCornerShape(12.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "신고",
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.primRed
            )
        }

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .clickable { onCancel() }
                .fillMaxWidth()
                .background(color = HambugTheme.colors.bgNormal, RoundedCornerShape(12.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "취소",
                style = HambugTheme.typography.body02Prominent,
                color = HambugTheme.colors.textBody
            )
        }
    }
}
