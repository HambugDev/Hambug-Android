package desktop.hambug.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import desktop.hambug.MainActivity
import desktop.hambug.R

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("fcm", "fcm 메시지 수신 - data: ${message.data}")
        Log.d("fcm", "fcm 메시지 수신 - notification: ${message.notification}")

        val title = message.notification?.title
            ?: message.data["title"]
            ?: "햄버그"
        val body = message.notification?.body
            ?: message.data["body"]
            ?: "새로운 알림이 도착했습니다"

        // 시스템 알림 표시
        showNotification(title, body)
    }

    override fun onNewToken(token: String) {
        Log.d("fcm", "fcm 새로운 토큰 발급: $token")

        // TODO: 서버에 토큰 전송
    }

    private fun showNotification(title: String, body: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 알림 클릭 시 앱 실행
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // 알림 생성
        val notification = NotificationCompat.Builder(this, "hambug_default_channel")
            .setSmallIcon(R.drawable.ic_noti)
            .setColor(ContextCompat.getColor(this, R.color.noti_bg))
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        // 각 알림에 고유한 ID 부여
        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
