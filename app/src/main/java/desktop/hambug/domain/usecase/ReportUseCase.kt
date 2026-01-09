package desktop.hambug.domain.usecase

import desktop.hambug.domain.repository.CommunityRepository
import javax.inject.Inject

class ReportUseCase @Inject constructor(
    private val repository: CommunityRepository
) {
    suspend operator fun invoke(targetId: Int, reportType: String, reportContent: String): Result<Unit> {
        return runCatching {
            repository.report(targetId, reportType, reportContent)
        }
    }
}
