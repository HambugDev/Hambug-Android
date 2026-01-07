package desktop.hambug.domain.usecase.fcm

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetFcmTokenUseCase @Inject constructor() {
    suspend operator fun invoke(): Result<String> = runCatching {
        FirebaseMessaging.getInstance().token.await()
    }
}
