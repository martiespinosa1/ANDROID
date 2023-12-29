package com.example.hagmanapp

sealed class Routes(val route: String) {
    object Splash:Routes("SplashScreen")
    object Menu:Routes("MenuScreen")
    object Game:Routes("GameScreen/{selectedDifficulty}") {
        fun createRoute(selectedDifficulty: String) = "GameScreen/$selectedDifficulty"
    }
    object Result:Routes("ResultScreen/{hasWon}") {
        fun createRoute(hasWon: Boolean) = "ResultScreen/$hasWon"
    }
}
