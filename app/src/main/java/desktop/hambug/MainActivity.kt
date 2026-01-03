package desktop.hambug

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
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
import desktop.hambug.presentation.ui.component.HambugBottomNav
import desktop.hambug.presentation.ui.component.SplashScreen
import desktop.hambug.presentation.home.HomeScreen
import desktop.hambug.presentation.login.LoginScreen
import desktop.hambug.presentation.my.MyActivityScreen
import desktop.hambug.presentation.my.MypageScreen
import desktop.hambug.presentation.noti.NotificationScreen
import desktop.hambug.presentation.ui.theme.HambugTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isLoading by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        // 스플래시 화면 설정
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        // 콘텐츠를 화면 끝까지 확장
        enableEdgeToEdge()

        // 알림 채널 생성 (Android 8.0 이상)
        createNotificationChannel()

        lifecycleScope.launch {
            delay(1500)
            isLoading = false
        }

        setContent {
            HambugTheme {
                if (isLoading) {
                    SplashScreen()
                } else {
                    HambugApp()
                }
            }
        }
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
}

@Composable
fun HambugApp(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    // 로그아웃 이벤트 구독
    LaunchedEffect(Unit) {
        mainViewModel.logoutEvent.collectLatest {
            Log.d("auth", "로그아웃 이벤트 수신. 로그인 화면으로 이동")
            navController.navigate("login") {
                // 스택 모두 제거
                popUpTo(navController.graph.id) { inclusive = true }
                launchSingleTop = true
            }
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
                HambugBottomNav(
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
            startDestination = "login"
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
            composable("write") {
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
        }
    }
}
