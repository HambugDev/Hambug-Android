package desktop.hambug.presentation.login

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import timber.log.Timber
import java.util.UUID

object AppleLoginHelper {
    private const val CLIENT_ID = "com.hambug.auth.apple.android"
    private const val REDIRECT_URI = "https://hambug.p-e.kr/api/auth/apple/android/callback"
    private const val APPLE_AUTH_URL = "https://appleid.apple.com/auth/authorize"

    fun startAppleLogin(context: Context) {
        val authUrl = buildAppleAuthUrl()
        Timber.d("apple 인증 요청 URL: $authUrl")

        val customTabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .build()

        try {
            customTabsIntent.launchUrl(context, authUrl.toUri())
        } catch (e: Exception) {
            Timber.e(e, "apple 로그인 customTab 실행 실패")
        }
    }

    private fun buildAppleAuthUrl(): String {
        val nonce = UUID.randomUUID().toString()

        return APPLE_AUTH_URL.toUri().buildUpon()
            .appendQueryParameter("client_id", CLIENT_ID)
            .appendQueryParameter("redirect_uri", REDIRECT_URI)
            .appendQueryParameter("response_type", "code id_token")
            .appendQueryParameter("response_mode", "fragment")
//            .appendQueryParameter("scope", "name email")
            .appendQueryParameter("nonce", nonce)
            .build()
            .toString()
    }

    fun extractIdentityToken(uri: Uri): String? {
        val fragment = uri.fragment ?: return null

        return fragment.split("&")
            .find { it.startsWith("id_token=") }
            ?.substringAfter("id_token=")
    }
}
