package desktop.hambug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import desktop.hambug.presentation.community.CommunityScreen
import desktop.hambug.presentation.community.PostDetailScreen
import desktop.hambug.presentation.ui.component.HambugBottomNav
import desktop.hambug.presentation.ui.component.SplashScreen
import desktop.hambug.presentation.home.HomeScreen
import desktop.hambug.presentation.login.LoginScreen
import desktop.hambug.presentation.my.MyActivityScreen
import desktop.hambug.presentation.my.MypageScreen
import desktop.hambug.presentation.noti.NotificationScreen
import desktop.hambug.presentation.ui.theme.HambugTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isLoading by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        // 스플래시 화면 설정
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

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
}

@Composable
fun HambugApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    val showBottomBar = when (currentRoute) {
        "login", "bell", "community_detail", "my_activity" -> false
        else -> true
    }

    Scaffold(
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
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding),
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
            composable("login") {
                LoginScreen(navController = navController)
            }
            composable("bell") {
                NotificationScreen(navController = navController)
            }
            composable("community_detail") {
                PostDetailScreen(navController = navController)
            }
            composable("my_activity") {
                MyActivityScreen(navController = navController)
            }
        }
    }
}
