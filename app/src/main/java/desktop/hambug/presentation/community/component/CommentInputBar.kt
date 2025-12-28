package desktop.hambug.presentation.community.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.ui.theme.HambugTheme

@Composable
fun CommentInputBar(
    commentText: String,
    onTextChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    val isEnabled = commentText.trim().isNotEmpty()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = HambugTheme.colors.bgWhite,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .border(width = 1.dp, color = HambugTheme.colors.bgDarker, shape = RoundedCornerShape(4.dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                value = commentText,
                onValueChange = onTextChange,
                textStyle = HambugTheme.typography.body02.copy(
                    color = HambugTheme.colors.textBody
                ),
                decorationBox = { innerTextField ->
                    if (commentText.isEmpty()) {
                        Text(
                            text = "댓글을 입력해주세요",
                            style = HambugTheme.typography.body02,
                            color = HambugTheme.colors.borderDefault
                        )
                    }
                    innerTextField()
                }
            )

            TextButton(
                onClick = {
                    onSubmit()
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
                enabled = isEnabled
            ) {
                Text(
                    text = "등록",
                    style = HambugTheme.typography.body03,
                    color = if (isEnabled) {
                        HambugTheme.colors.primRed
                    } else {
                        HambugTheme.colors.borderDisabled
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun CommentInputBarPreview() {
    HambugTheme {
//        CommentInputBar()
    }
}
