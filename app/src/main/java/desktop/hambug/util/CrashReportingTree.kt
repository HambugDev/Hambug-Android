package desktop.hambug.util

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority < Log.WARN) return

        try {
            val crashlytics = FirebaseCrashlytics.getInstance()

            val logMessage = if (tag != null) "[$tag] $message" else message
            crashlytics.log(logMessage)  // Crashlytics에 로그 기록

            // 예외가 있으면 기록
            t?.let {
                crashlytics.recordException(it)
            }
        } catch (e: Exception) {
            // Crashlytics 로깅 실패는 무시
        }
    }
}
