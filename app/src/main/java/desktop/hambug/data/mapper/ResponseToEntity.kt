package desktop.hambug.data.mapper

import desktop.hambug.data.dto.home.HomeBurgerResponse
import desktop.hambug.data.dto.my.UserInfoResponse
import desktop.hambug.data.dto.community.BoardDetailResponse
import desktop.hambug.data.dto.community.BoardItem
import desktop.hambug.data.dto.community.BoardsResponse
import desktop.hambug.data.dto.community.CommentItem
import desktop.hambug.data.dto.community.MyBoardItem
import desktop.hambug.data.dto.community.MyCommentItem
import desktop.hambug.data.dto.fcm.NotiItem
import desktop.hambug.data.dto.home.HomeBoardResponse
import desktop.hambug.domain.model.Board
import desktop.hambug.domain.model.BoardDetail
import desktop.hambug.domain.model.BoardPage
import desktop.hambug.domain.model.Comment
import desktop.hambug.domain.model.HomeBoard
import desktop.hambug.domain.model.HomeBurger
import desktop.hambug.domain.model.MyBoard
import desktop.hambug.domain.model.MyComment
import desktop.hambug.domain.model.Noti
import desktop.hambug.domain.model.UserInfo

fun HomeBurgerResponse.toEntity(): HomeBurger {
    return HomeBurger(
        id = this.id,
        imageUrl = this.menuImage,
        franchiseName = this.franchise,
        name = this.menuName,
        description = this.menuDescription
    )
}

fun HomeBoardResponse.toEntity(): HomeBoard? {
    val validId = this.id ?: return null

    return HomeBoard(
        id = validId,
        title = this.title,
        content = this.content,
        category = this.category,
        imageUrl = if (this.imageUrls.isEmpty()) null else imageUrls[0],
        createdAt = this.createdAt,
        likeCount = this.likeCount,
        commentCount = this.commentCount
    )
}

fun UserInfoResponse.toEntity(): UserInfo {
    return UserInfo(
        userId = this.userId,
        nickname = this.nickname,
        profileImageUrl = this.profileImageUrl,
        loginType = this.loginType
    )
}

fun BoardItem.toEntity(): Board? {
    val validId = this.id ?: return null

    return Board(
        id = validId,
        title = this.title,
        content = this.content,
        imageUrl = if (this.imageUrls.isEmpty()) null else this.imageUrls[0],
        authorNickname = this.authorNickname,
        createdAt = this.createdAt,
        likeCount = this.likeCount,
        commentCount = this.commentCount
    )
}

fun BoardsResponse.toEntity(): BoardPage {
    return BoardPage(
        // mapNotNull을 사용하여 id가 없는 아이템 제거
        content = this.content.mapNotNull { it.toEntity() },
        nextCursorId = if (this.nextPage) this.nextCursorId else -1,
        nextPage = this.nextPage
    )
}

fun BoardDetailResponse.toEntity(): BoardDetail {
    return BoardDetail(
        id = this.id,
        title = this.title,
        content = this.content,
        imageUrls = if (this.imageUrls.isEmpty()) null else this.imageUrls,
        authorNickname = this.authorNickname,
        authorProfileImageUrl = this.authorProfileImageUrl,
        createdAt = this.createdAt,
        likeCount = this.likeCount,
        commentCount = this.commentCount,
        isLiked = this.isLiked,
        isAuthor = this.isAuthor
    )
}

fun CommentItem.toEntity(): Comment? {
    val validId = this.id ?: return null

    return Comment(
        id = validId,
        content = this.content,
        authorNickname = this.authorNickname,
        authorProfileImageUrl = this.authorProfileImageUrl,
        isAuthor = this.isAuthor,
        createdAt = this.createdAt
    )
}

fun MyBoardItem.toEntity(): MyBoard? {
    val validId = this.id ?: return null

    return MyBoard(
        id = validId,
        title = this.title,
        authorNickname = this.authorNickname,
        likeCount = this.likeCount,
        commentCount = this.commentCount,
        imageUrl = if (this.imageUrls.isEmpty()) null else this.imageUrls[0],
        createdAt = this.createAt
    )
}

fun MyCommentItem.toEntity(): MyComment? {
    val validId = this.boardId ?: return null

    return MyComment(
        boardId = validId,
        boardTitle = this.title,
        commentId = this.commentId,
        commentContent = this.content,
        createdAt = this.createdAt
    )
}

fun NotiItem.toEntity(): Noti? {
    val validId = this.targetId ?: return null

    return Noti(
        notiId = this.id,
        title = this.title,
        content = this.content,
        type = this.type,
        targetId = validId,
        thumbnailUrl = this.thumbnailUrl,
        createdAt = this.createdAt
    )
}
