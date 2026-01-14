package desktop.hambug.domain.usecase.community

import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class ReportUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(targetId: Int, reportType: String, title: String, content: String): Result<Unit> {
        return runCatching {
            repository.report(targetId, reportType, title, content)
        }
    }
}
