package desktop.hambug.data.mapper

import desktop.hambug.data.dto.HomeBurgerData
import desktop.hambug.domain.model.HomeBurger

fun HomeBurgerData.toEntity(): HomeBurger {
    return HomeBurger(
        id =  this.id,
        imageUrl = this.menuImage,
        franchiseName = this.franchise,
        name = this.menuName,
        description = this.menuDescription
    )
}
