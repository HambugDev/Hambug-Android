package desktop.hambug.presentation.util

import desktop.hambug.domain.model.Noti

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

fun Noti.getDisplayMessage(): String {
    if (type != "LIKE_NOTIFICATION") return content

    return when {
        title.contains("외") -> "${title}이 $content"
        else -> "${title}님이 $content"
    }
}
