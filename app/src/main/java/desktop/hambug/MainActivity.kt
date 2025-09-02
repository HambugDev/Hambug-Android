package desktop.hambug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import desktop.hambug.presentation.ui.component.HambugBottomNav
import desktop.hambug.presentation.ui.component.HambugTopAppBar
import desktop.hambug.presentation.ui.home.HomeScreen
import desktop.hambug.presentation.ui.theme.HambugTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 스플래시 화면 설정
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            HambugTheme {
                HambugApp()
            }
        }
    }
}

@Composable
fun HambugApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    Scaffold(
        topBar = {
            val title = when (currentRoute) {
                "home" -> "홈"
                "community" -> "커뮤니티"
                "my" -> "마이"
                else -> ""
            }
            HambugTopAppBar(
                title = title,
                onBellClick = {}
            )
        },
        bottomBar = {
            HambugBottomNav(
                currentRoute = currentRoute,
                onNavItemClick = { route ->
                    if (navController.currentDestination?.route != route) {
                        navController.navigate(route)
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable("home") {
                HomeScreen()
            }
            composable("community") {
                Text("커뮤니티 화면")
            }
            composable("my") {
                Text("마이 화면")
            }
        }
    }
}
