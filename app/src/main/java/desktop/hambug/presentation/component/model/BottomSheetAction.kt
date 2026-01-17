package desktop.hambug.presentation.component.model

enum class CornerType {
    TOP, BOTTOM, ALL
}

data class BottomSheetAction(
    val text: String,
    val onClick: () -> Unit,
    val isNegative: Boolean = false,
    val cornerType: CornerType = CornerType.ALL
)
