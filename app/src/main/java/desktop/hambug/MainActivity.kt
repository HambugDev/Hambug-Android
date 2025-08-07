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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import desktop.hambug.ui.component.HambugBottomNav
import desktop.hambug.ui.theme.HambugTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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
                Text("홈 화면")
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
