package com.example.hagmanapp

sealed class Routes(val route: String) {
    object Splash:Routes("SplashScreen")
    object Menu:Routes("MenuScreen")
    object Game:Routes("GameScreen")
    object Result:Routes("ResultScreen/{secretNumber}") {
        fun createRoute(secretNumber: Int) = "ResultScreen/$secretNumber"
    }
}
