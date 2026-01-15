package desktop.hambug.domain.usecase.fcm

import desktop.hambug.domain.model.Noti
import desktop.hambug.domain.repository.FcmRepository
import javax.inject.Inject

class GetNotisUseCase @Inject constructor(
    private val repository: FcmRepository
){
    suspend operator fun invoke(): Result<List<Noti>> {
        return runCatching {
            repository.getNotis()
        }
    }
}
