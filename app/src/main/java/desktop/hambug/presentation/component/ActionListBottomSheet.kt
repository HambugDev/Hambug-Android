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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import desktop.hambug.presentation.component.model.BottomSheetAction
import desktop.hambug.presentation.component.model.CornerType
import desktop.hambug.presentation.ui.theme.HambugTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionListBottomSheet(
    actions: List<BottomSheetAction>,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        dragHandle = null,
        containerColor = HambugTheme.colors.bgWhite,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            actions.forEach { action ->
                if (action.isNegative && actions.size == 3) {
                    HorizontalDivider(thickness = 0.5.dp, color = HambugTheme.colors.bgDarker)
                }

                val shape = when (action.cornerType) {
                    CornerType.TOP -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    CornerType.BOTTOM -> RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    else -> RoundedCornerShape(12.dp)
                }

                Box(
                    modifier = Modifier
                        .clickable {
                            action.onClick()
                            onDismiss()
                        }
                        .fillMaxWidth()
                        .background(color = HambugTheme.colors.bgNormal, shape = shape)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = action.text,
                        style = HambugTheme.typography.body02Prominent,
                        color = if (action.isNegative) HambugTheme.colors.primRed else HambugTheme.colors.textBody
                    )
                }

                if (action.isNegative) {
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}
