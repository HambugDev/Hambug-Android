package desktop.hambug.domain.usecase

import android.content.Context
import desktop.hambug.domain.repository.KakaoLoginRepository
import javax.inject.Inject

class KakaoLoginUseCase @Inject constructor(
    private val repository: KakaoLoginRepository
) {
    suspend operator fun invoke(context: Context): String {
        return repository.login(context)
    }
}
