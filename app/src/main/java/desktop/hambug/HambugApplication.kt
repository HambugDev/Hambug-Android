package desktop.hambug

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import desktop.hambug.util.CrashReportingTree
import timber.log.Timber

@HiltAndroidApp
class HambugApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 카카오 SDK 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)

        // Timber 설정
        setupTimber()

        // 앱 시작 로그
        Timber.d("HambugApplication - BuildType: ${if (BuildConfig.DEBUG) "debug" else "release"}")
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
                }
            })
        } else {
            // Crashlytics로 전송
            Timber.plant(CrashReportingTree())
        }
    }
}
