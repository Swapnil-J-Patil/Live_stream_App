package com.swapnil.livestreamapp.presentation.main_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cleanarchitectureproject.presentation.main_screen.components.navbar.BottomNavAnimation
import com.swapnil.livestreamapp.presentation.Navbar
import com.swapnil.livestreamapp.presentation.chat_screen.ChatScreen
import com.swapnil.livestreamapp.presentation.for_you_screen.ForYouScreen
import com.swapnil.livestreamapp.presentation.match_screen.MatchScreen
import com.swapnil.livestreamapp.presentation.profile_screen.ProfileScreen
import com.swapnil.livestreamapp.presentation.search_screen.SearchScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope,
    isDarkTheme: Boolean,
    onToggle: () -> Unit,
    onLogout: () -> Unit
) {
    val screen = listOf(
        Navbar.ForYou,
        Navbar.Search,
        Navbar.Chat,
        Navbar.Match,
        Navbar.Profile
    )

    val configuration = LocalConfiguration.current
    val isTab = configuration.screenWidthDp.dp > 600.dp

    var selectedTab by rememberSaveable(
        stateSaver = Saver(
        save = { it },
        restore = { it }
    )) { mutableStateOf(0) }
    var bottomBarVisibility by remember { mutableStateOf(true) }
    val isMarketScreen by mainScreenViewModel.currentTab.collectAsState()
    var isChatClicked by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (isMarketScreen) {
            "search" -> {
                SearchScreen()
            }

            "match" -> {
                MatchScreen()
            }

            "profile" -> {
                ProfileScreen()
            }

            "chat" -> {
                ChatScreen()
            }

            else -> {

                ForYouScreen()
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AnimatedVisibility(
                visible = bottomBarVisibility,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
                BottomNavAnimation(
                    screens = screen,
                    selectedTab = selectedTab,  // Pass selectedTab
                    onClick = { tab ->
                        selectedTab = tab
                        when (tab) {
                            0 -> mainScreenViewModel.toggleTab("for_you")
                            1 -> mainScreenViewModel.toggleTab("search")
                            2 -> {
                                mainScreenViewModel.toggleTab("chat")
                                isChatClicked = true}
                            3 -> mainScreenViewModel.toggleTab("match")
                            4 -> mainScreenViewModel.toggleTab("profile")

                        }
                    },
                    isChatClicked=isChatClicked
                )
            }

        }
    }
}
