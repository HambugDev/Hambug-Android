package desktop.hambug.domain.usecase

import android.net.Uri
import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class CreateBoardUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String,
        category: String,
        imageUris: List<Uri> = emptyList()
    ): Result<Int> {
        return runCatching {
            if (imageUris.isEmpty()) {
                // 이미지 없음
                repository.createBoard(title, content, category)
            } else {
                // 이미지 있음
                repository.createBoardWithImages(title, content, category, imageUris)
            }
        }
    }
}
