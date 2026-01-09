package desktop.hambug.data.dto.report

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String
)
