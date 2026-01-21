package desktop.hambug.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun ForceUpdateDialog(
    onUpdateClick: () -> Unit
) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = false,    // 뒤로가기 막기
            dismissOnClickOutside = false  // dialog 외부 클릭 막기
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = HambugTheme.colors.bgWhite
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 36.dp, bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "업데이트 안내",
                        style = HambugTheme.typography.title02,
                        color = HambugTheme.colors.textHeadline
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "해당 버전은 더 이상 지원하지 않습니다.",
                        style = HambugTheme.typography.body03,
                        color = HambugTheme.colors.textDisabled,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(Modifier.height(24.dp))

                    Box(
                        modifier = Modifier
                            .clickable { onUpdateClick() }
                            .fillMaxWidth()
                            .background(color = HambugTheme.colors.primRed, shape = RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "업데이트 하러 가기",
                            style = HambugTheme.typography.body02Prominent,
                            color = HambugTheme.colors.bgWhite
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun UpdataCheckDialogPreview() {
    HambugTheme {
//        ForceUpdateDialog()
    }
}
