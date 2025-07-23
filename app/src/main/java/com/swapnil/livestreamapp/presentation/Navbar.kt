package com.swapnil.livestreamapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CandlestickChart
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.CandlestickChart
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.InsertChart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Navbar(
    val title: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector
) {
    object ForYou: Navbar("For You", Icons.Filled.Explore, Icons.Outlined.Explore)
    object Search: Navbar("Search", Icons.Filled.Search, Icons.Outlined.Search)
    object Chat: Navbar("Chat", Icons.Filled.ChatBubble, Icons.Outlined.ChatBubble)
    object Match: Navbar("Match", Icons.Filled.Favorite, Icons.Outlined.Favorite)
    object Profile: Navbar("Profile", Icons.Filled.Person, Icons.Outlined.Person)

}