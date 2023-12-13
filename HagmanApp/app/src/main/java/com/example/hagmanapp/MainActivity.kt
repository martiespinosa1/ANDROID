package com.example.hagmanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hagmanapp.ui.theme.HagmanAppTheme
import com.example.hagmanapp.SplashScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HagmanAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Splash.route
                    ) {
                        composable(Routes.Splash.route) { SplashScreen(navigationController) }
                        composable(Routes.Menu.route) { Menu(navigationController) }
                        composable(Routes.Game.route) { Game(navigationController) }
                        composable(
                            Routes.Result.route,
                            arguments = listOf(navArgument("secretNumber") {type = NavType.IntType})
                        ) { backStackEntry ->
                            Result(
                                navigationController
                            )
                        }
                    }
                }
            }
        }
    }
}
