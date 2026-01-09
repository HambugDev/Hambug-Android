package desktop.hambug.presentation.util

fun String.toKoreanCategory(): String {
    return when (this) {
        "FREE_TALK" -> "자유잡담"
//        "FRANCHISE" -> "햄버거리뷰"
//        "HANDMADE" -> "햄버거리뷰"
        "REVIEW" -> "햄버거리뷰"
        "RECOMMENDATION" -> "맛집추천"
        else -> this
    }
}
