package desktop.hambug

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import desktop.hambug.presentation.community.CommunityScreen
import desktop.hambug.presentation.community.BoardDetailScreen
import desktop.hambug.presentation.community.BoardWriteScreen
import desktop.hambug.presentation.community.ReportScreen
import desktop.hambug.presentation.navigation.AppBottomBar
import desktop.hambug.presentation.splash.SplashScreen
import desktop.hambug.presentation.home.HomeScreen
import desktop.hambug.presentation.login.LoginScreen
import desktop.hambug.presentation.my.MyActivityScreen
import desktop.hambug.presentation.my.MypageScreen
import desktop.hambug.presentation.noti.NotificationScreen
import desktop.hambug.presentation.component.ForceUpdateDialog
import desktop.hambug.presentation.ui.theme.HambugTheme
import desktop.hambug.util.VersionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var versionManager: VersionManager

    private val _fcmBoardId = MutableStateFlow<Int?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        // 콘텐츠를 화면 끝까지 확장
        enableEdgeToEdge()

        // 알림 채널 생성 (Android 8.0 이상)
        createNotificationChannel()

        // 앱 꺼진 상태에서 알림 클릭 시 여기서 검사
        checkIntentForBoardId(intent)

        // showNativeSplash가 true인 동안 계속 표시됨 (기본 스플래시)
        splashScreen.setKeepOnScreenCondition {
            mainViewModel.showNativeSplash.value
        }

        setContent {
            val fcmBoardId by _fcmBoardId.collectAsStateWithLifecycle()

            HambugTheme {
                val showNativeSplash by mainViewModel.showNativeSplash.collectAsStateWithLifecycle()
                val startDestination by mainViewModel.startDestination.collectAsStateWithLifecycle()
                val needForceUpdate by mainViewModel.needForceUpdate.collectAsStateWithLifecycle()

                if (needForceUpdate) {
                    ForceUpdateDialog(
                        onUpdateClick = { versionManager.openPlayStore(this) }
                    )
                } else {
                    // 기본 스플래시가 끝난 후에 화면 그림
                    if (!showNativeSplash) {
                        val destination = startDestination

                        if (destination == null) {
                            SplashScreen()  // 커스텀 스플래시
                        } else {
                            HambugApp(
                                startDestination = destination,
                                fcmBoardId = fcmBoardId,
                                onConsumeFcmId = { _fcmBoardId.value = null }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // 앱 켜진 상태에서 알림 클릭 시 여기서 검사
        checkIntentForBoardId(intent)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "hambug_default_channel",
            "햄버그 알림",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableVibration(true)
        }

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    private fun checkIntentForBoardId(intent: Intent?) {
        if (intent?.hasExtra("boardId") == true) {
            val boardId = intent.getIntExtra("boardId", -1)
            if (boardId != -1) {
                _fcmBoardId.value = boardId
                // 중복 처리 방지
                intent.removeExtra("boardId")
            }
        }
    }
}

@Composable
fun HambugApp(
    startDestination: String,
    mainViewModel: MainViewModel = hiltViewModel(),
    fcmBoardId: Int? = null,
    onConsumeFcmId: () -> Unit = {}
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    // 로그아웃 이벤트 처리
    LaunchedEffect(Unit) {
        mainViewModel.logoutEvent.collectLatest {
            navController.navigate("login") {
                // 스택 모두 제거
                popUpTo(navController.graph.id) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    // fcmBoardId 값이 들어오면 해당 화면으로 이동
    LaunchedEffect(fcmBoardId) {
        if (fcmBoardId != null) {
            navController.navigate("community_detail/$fcmBoardId")
            onConsumeFcmId()
        }
    }

    val showBottomBar = when (currentRoute) {
        "home", "community", "my" -> true
        else -> false
    }

    Scaffold(
        // padding 중복을 막기 위해 모든 Insets 차단
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBar) {
                AppBottomBar(
                    currentRoute = currentRoute,
                    onNavItemClick = { route ->
                        if (navController.currentDestination?.route != route) {
                            navController.navigate(route)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            // 메인 그래프
            navigation(
                startDestination = "home",
                route = "main_graph"
            ) {
                composable("home") {
                    HomeScreen(navController = navController)
                }
                composable("community") {
                    CommunityScreen(navController = navController)
                }
                composable("my") {
                    MypageScreen(navController = navController)
                }
            }

            composable("login") {
                LoginScreen(navController = navController)
            }
            composable("bell") {
                NotificationScreen(navController = navController)
            }
            composable(
                route = "write?boardId={boardId}",
                arguments = listOf(
                    navArgument("boardId") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) {
                BoardWriteScreen(navController = navController)
            }
            composable(
                route = "community_detail/{boardId}?isNewBoard={isNewBoard}",
                arguments = listOf(
                    navArgument("boardId") { type = NavType.IntType },
                    navArgument("isNewBoard") {
                        type = NavType.BoolType
                        defaultValue = false
                    }
                )
            ) {
                BoardDetailScreen(navController = navController)
            }
            composable("my_activity") {
                MyActivityScreen(navController = navController)
            }
            composable(
                route = "report/{reportType}/{targetId}",
                arguments = listOf(
                    navArgument("reportType") { type = NavType.StringType },
                    navArgument("targetId") { type = NavType.IntType }
                )
            ) {
                ReportScreen(navController = navController)
            }
        }
    }
}
