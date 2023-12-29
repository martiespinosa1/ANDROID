package com.example.hagmanapp

sealed class Routes(val route: String) {
    object Splash:Routes("SplashScreen")
    object Menu:Routes("MenuScreen")
    object Game:Routes("GameScreen/{selectedDifficulty}") {
        fun createRoute(selectedDifficulty: String) = "GameScreen/$selectedDifficulty"
    }
    object Result:Routes("ResultScreen/{hasWon}/{palabra}/{fallos}/{diff}") {
        fun createRoute(hasWon: Boolean, palabra: String, fallos: Int, diff: String) = "ResultScreen/$hasWon/$palabra/$fallos/$diff"
    }
}
