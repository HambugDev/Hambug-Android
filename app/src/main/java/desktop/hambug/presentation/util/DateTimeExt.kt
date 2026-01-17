package desktop.hambug.presentation.util

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeParseException

/**
 * 아이템 생성시간 기준 상대시간으로 변환
 *
 * @return (예: "5분 전", "2시간 전")
 */
fun String.toTimeAgoString(): String {
    if (this.isBlank()) return "날짜 없음"

    val createdInstant: Instant
    try {
        // LocalDateTime으로 파싱 후 시스템 타임존으로 변환
        createdInstant = LocalDateTime.parse(this)
            .atZone(ZoneId.systemDefault())
            .toInstant()
    } catch (e: DateTimeParseException) {
        return "날짜 형식 오류"
    }

    val now = Instant.now()
    val duration = Duration.between(createdInstant, now)  // 시간 차이

    val minutes = duration.toMinutes()
    val hours = duration.toHours()
    val days = duration.toDays()

    return when {
        // 1분 미만
        minutes < 1 -> "방금 전"
        // 1시간 미만
        minutes < 60 -> "${minutes}분 전"
        // 24시간 미만
        hours < 24 -> "${hours}시간 전"
        // 7일 미만
        days < 7 -> "${days}일 전"
        // 30일 미만
        days < 30 -> "${days / 7}주 전"
        // 1년 미만
        days < 365 -> "${days / 30}달 전"
        // 1년 이상
        else -> "${days / 365}년 전"
    }
}
