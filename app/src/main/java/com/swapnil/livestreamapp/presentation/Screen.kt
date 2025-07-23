package com.swapnil.livestreamapp.presentation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object SplashScreen: Screen("splash_screen")

}