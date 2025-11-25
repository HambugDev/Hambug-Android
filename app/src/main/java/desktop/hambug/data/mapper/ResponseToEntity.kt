package desktop.hambug.data.mapper

import desktop.hambug.data.dto.HomeBurgerData
import desktop.hambug.data.dto.UserInfoData
import desktop.hambug.domain.model.HomeBurger
import desktop.hambug.domain.model.UserInfo

fun HomeBurgerData.toEntity(): HomeBurger {
    return HomeBurger(
        id =  this.id,
        imageUrl = this.menuImage,
        franchiseName = this.franchise,
        name = this.menuName,
        description = this.menuDescription
    )
}

fun UserInfoData.toEntity(): UserInfo {
    return UserInfo(
        userId = this.userId,
        nickname = this.nickname,
        profileImageUrl = this.profileImageUrl
    )
}
