package com.swapnil.livestreamapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.swapnil.livestreamapp.presentation.main_screen.MainScreen
import com.swapnil.livestreamapp.presentation.splash_screen.SplashScreen
import com.swapnil.livestreamapp.presentation.ui.theme.LiveStreamAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiveStreamAppTheme {
                val navController = rememberNavController()

                SharedTransitionLayout {

                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {

                        composable(
                            route = Screen.SplashScreen.route
                        ) {
                            SplashScreen(navController,this@MainActivity)
                        }
                        composable(
                            route = Screen.MainScreen.route,
                            enterTransition = { EnterTransition.None },
                            exitTransition = { ExitTransition.None },
                            popEnterTransition = { EnterTransition.None },
                            popExitTransition = { ExitTransition.None }
                        ) {
                            MainScreen(navController, animatedVisibilityScope = this,
                                isDarkTheme = true, onToggle = {

                                },
                                onLogout = {
                                    /*navController.navigate(Screen.AuthScreen.route) {
                                        popUpTo(Screen.MainScreen.route) { inclusive = true }
                                    }*/
                                    //val context = LocalContext.current
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    finish()
                                    startActivity(intent)

                                })

                        }



                    }
                }
            }
        }
    }
}

