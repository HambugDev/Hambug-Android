package desktop.hambug.data.dto.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeBurgerResponse(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("menuImage")
    val menuImage: String = "",
    @SerialName("franchise")
    val franchise: String = "",
    @SerialName("menuName")
    val menuName: String = "",
    @SerialName("menuDescription")
    val menuDescription: String = ""
)
