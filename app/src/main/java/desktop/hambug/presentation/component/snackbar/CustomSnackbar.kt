package desktop.hambug.presentation.component.snackbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import desktop.hambug.R
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.presentation.ui.theme.SnackbarBg
import desktop.hambug.presentation.ui.theme.SnackbarText

@Composable
fun CustomSnackbar(
    snackbarData: SnackbarData
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color = SnackbarBg)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.logo_snackbar),
            contentDescription = null
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = snackbarData.visuals.message,
            style = HambugTheme.typography.body03,
            color = SnackbarText
        )
    }
}

@Preview
@Composable
fun CustomSnackbarPreview() {
    HambugTheme {
//        CustomSnackbar()
    }
}
