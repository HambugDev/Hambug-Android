package desktop.hambug.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import desktop.hambug.MainActivity
import timber.log.Timber

class AppleLoginCallbackActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleCallback(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleCallback(intent)
    }

    private fun handleCallback(intent: Intent?) {
        val data = intent?.data
        Timber.d("apple 인증 응답 URL: $data")

        // identity token 추출
        val identityToken = data?.let { AppleLoginHelper.extractIdentityToken(it) }
        Timber.d("apple identityToken: $identityToken")

        if (identityToken != null) {
            Timber.d("apple identity token 획득")

            // MainActivity로 identity token 전달
            val mainIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("apple_identity_token", identityToken)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(mainIntent)
        } else {
            Timber.e("apple identity token 추출 실패")
        }

        finish()
    }
}
