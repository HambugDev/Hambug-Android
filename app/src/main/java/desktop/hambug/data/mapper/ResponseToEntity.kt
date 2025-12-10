package desktop.hambug.data.mapper

import desktop.hambug.data.dto.HomeBurgerData
import desktop.hambug.data.dto.UserInfoData
import desktop.hambug.data.dto.community.BoardDetailData
import desktop.hambug.data.dto.community.BoardItem
import desktop.hambug.data.dto.community.BoardsData
import desktop.hambug.domain.model.Board
import desktop.hambug.domain.model.BoardDetail
import desktop.hambug.domain.model.BoardPage
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

fun BoardItem.toEntity(): Board {
    return Board(
        id = this.id,
        title = this.title,
        content = this.content,
        imageUrl = if (this.imageUrls.isEmpty()) null else this.imageUrls[0],
        authorNickname = this.authorNickname,
        createdAt = this.createdAt,
        likeCount = this.likeCount,
        commentCount = this.commentCount
    )
}

fun BoardsData.toEntity(): BoardPage {
    return BoardPage(
        content = this.content.map { it.toEntity() },
        nextCursorId = this.netCursorId,
        nextPage = this.nextPage
    )
}

fun BoardDetailData.toEntity(): BoardDetail {
    return BoardDetail(
        id = this.id,
        title = this.title,
        content = this.content,
        imageUrls = if (this.imageUrls.isEmpty()) null else this.imageUrls,
        authorNickname = this.authorNickname,
        authorProfileImageUrl = this.authorProfileImageUrl,
        createdAt = this.createdAt,
        likeCount = this.likeCount,
        commentCount = this.commentCount
    )
}
