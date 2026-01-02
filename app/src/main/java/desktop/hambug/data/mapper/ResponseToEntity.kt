package desktop.hambug.data.mapper

import desktop.hambug.data.dto.home.HomeBurgerData
import desktop.hambug.data.dto.UserInfoData
import desktop.hambug.data.dto.community.BoardDetailData
import desktop.hambug.data.dto.community.BoardItem
import desktop.hambug.data.dto.community.BoardsData
import desktop.hambug.data.dto.community.CommentItem
import desktop.hambug.data.dto.community.MyBoardItem
import desktop.hambug.data.dto.community.MyCommentItem
import desktop.hambug.data.dto.home.HomeBoardData
import desktop.hambug.domain.model.Board
import desktop.hambug.domain.model.BoardDetail
import desktop.hambug.domain.model.BoardPage
import desktop.hambug.domain.model.Comment
import desktop.hambug.domain.model.HomeBoard
import desktop.hambug.domain.model.HomeBurger
import desktop.hambug.domain.model.MyBoard
import desktop.hambug.domain.model.MyComment
import desktop.hambug.domain.model.UserInfo

fun HomeBurgerData.toEntity(): HomeBurger {
    return HomeBurger(
        id = this.id,
        imageUrl = this.menuImage,
        franchiseName = this.franchise,
        name = this.menuName,
        description = this.menuDescription
    )
}

fun HomeBoardData.toEntity(): HomeBoard {
    return HomeBoard(
        id = this.id,
        title = this.title,
        content = this.content,
        category = this.category,
        imageUrl = if (this.imageUrls.isEmpty()) null else imageUrls[0],
        createdAt = this.createdAt,
        likeCount = this.likeCount,
        commentCount = this.commentCount
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
        commentCount = this.commentCount,
        isLiked = this.isLiked,
        isAuthor = this.isAuthor
    )
}

fun CommentItem.toEntity(): Comment {
    return Comment(
        id = this.id,
        content = this.content,
        authorNickname = this.authorNickname,
        authorProfileImageUrl = this.authorProfileImageUrl,
        isAuthor = this.isAuthor,
        createdAt = this.createdAt
    )
}

fun MyBoardItem.toEntity(): MyBoard {
    return MyBoard(
        id = this.id,
        title = this.title,
        likeCount = this.likeCount,
        commentCount = this.commentCount,
        imageUrl = if (this.imageUrls.isEmpty()) null else this.imageUrls[0],
        createdAt = this.createAt
    )
}

fun MyCommentItem.toEntity(): MyComment {
    return MyComment(
        boardId = this.boardId,
        boardTitle = this.title,
        commentId = this.commentId,
        commentContent = this.content,
        createdAt = this.createdAt
    )
}
