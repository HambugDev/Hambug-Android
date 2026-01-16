package desktop.hambug.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.pm.PackageManager
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.net.toUri
import timber.log.Timber

@Singleton
class VersionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        // Remote Config 설정
        val configSetting = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600  // 1시간
        }
        remoteConfig.setConfigSettingsAsync(configSetting)

        // 기본값 설정
        remoteConfig.setDefaultsAsync(
            mapOf(
                "android_min_version_code" to 1L
            )
        )
    }

    /**
     * Remote Config에서 버전 정보 가져오기
     */
    suspend fun getVersionInfo(): Result<Unit> {
        return try {
            remoteConfig.fetchAndActivate().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 현재 앱 버전 코드 가져오기
     */
    private fun getCurrentVersionCode(): Long {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.longVersionCode
        } catch (e: PackageManager.NameNotFoundException) {
            1L
        }
    }

    /**
     * 강제 업데이트가 필요한지 확인
     */
    fun isForceUpdateRequired(): Boolean {
        val currentVersionCode = getCurrentVersionCode()
        val minVersionCode = remoteConfig.getLong("android_min_version_code")
        return currentVersionCode < minVersionCode
    }

    /**
     * 구글 플레이 스토어로 이동
     */
    fun openPlayStore(context: Context) {
        val packageName = context.packageName
        try {
            // 스토어 앱으로 이동 시도
            val intent = android.content.Intent(
                android.content.Intent.ACTION_VIEW,
                "market://details?id=$packageName".toUri()
            )
            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // 스토어 앱이 없는 경우
            Timber.e(e, "Play 스토어 앱을 찾을 수 없습니다")
        }
    }
}
