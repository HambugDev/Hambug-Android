package desktop.hambug.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import desktop.hambug.R
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun TwoButtonDialog(
    title: String,
    content: String?,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = HambugTheme.colors.bgWhite
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 36.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_modal),
                    contentDescription = null
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = title,
                    style = HambugTheme.typography.title02,
                    color = HambugTheme.colors.textHeadline
                )

                if (content != null) {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = content,
                        style = HambugTheme.typography.body03,
                        color = HambugTheme.colors.textDisabled,
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(Modifier.height(24.dp))

                // 버튼 영역
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .clickable { onCancel() }
                            .weight(1f)
                            .background(color = HambugTheme.colors.bgDarker, shape = RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "취소",
                            style = HambugTheme.typography.body02Prominent,
                            color = HambugTheme.colors.textBody
                        )
                    }

                    Spacer(Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .clickable { onConfirm() }
                            .weight(1f)
                            .background(color = HambugTheme.colors.primRed, shape = RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "삭제",
                            style = HambugTheme.typography.body02Prominent,
                            color = HambugTheme.colors.bgWhite
                        )
                    }
                }
            }
        }
    }
}
