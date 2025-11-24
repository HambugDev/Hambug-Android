package desktop.hambug.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeBurgerResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: List<HomeBurgerData>,
    @SerialName("message")
    val message: String
)

@Serializable
data class HomeBurgerData(
    @SerialName("id")
    val id: Int,
    @SerialName("menuImage")
    val menuImage: String,
    @SerialName("franchise")
    val franchise: String,
    @SerialName("menuName")
    val menuName: String,
    @SerialName("menuDescription")
    val menuDescription: String
)
