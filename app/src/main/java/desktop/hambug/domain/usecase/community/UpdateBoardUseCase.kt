package desktop.hambug.domain.usecase.community

import android.net.Uri
import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class UpdateBoardUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(
        boardId: Int,
        title: String,
        content: String,
        category: String,
        oldImageUrls: List<String> = emptyList(),
        newImageUris: List<Uri> = emptyList()
    ): Result<Unit> {
        return runCatching {
            val hasImage = oldImageUrls.isNotEmpty() || newImageUris.isNotEmpty()

            if (hasImage) {
                // 게시물 수정 (이미지 포함)
                repository.updateBoardWithImages(
                    boardId = boardId,
                    title = title,
                    content = content,
                    category = category,
                    oldImageUrls = oldImageUrls,
                    newImageUris = newImageUris
                )
            } else {
                // 게시물 수정
                repository.updateBoard(
                    boardId = boardId,
                    title = title,
                    content = content,
                    category = category,
                )
            }
        }
    }
}
