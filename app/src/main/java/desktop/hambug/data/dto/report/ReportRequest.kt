package desktop.hambug.data.dto.report

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportRequest(
    @SerialName("targetId")
    val targetId: Int,
    @SerialName("targetType")
    val targetType: String,
    @SerialName("title")
    val title: String,
    @SerialName("reason")
    val reason: String
)
