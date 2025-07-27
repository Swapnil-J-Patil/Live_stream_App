package com.swapnil.livestreamapp.presentation.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swapnil.livestreamapp.presentation.for_you_screen.components.ShimmerGrid
import com.swapnil.livestreamapp.presentation.search_screen.components.CategoryRow
import com.swapnil.livestreamapp.presentation.search_screen.components.ShimmerScreenSearch
import com.swapnil.livestreamapp.presentation.state.UserIntent
import com.swapnil.livestreamapp.presentation.ui.theme.orange

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val state by searchViewModel.state.collectAsState()
    val listState = rememberLazyGridState()
    var searchQuery by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        searchViewModel.handleIntent(UserIntent.LoadUsers)
    }

    when {
        state.isLoading|| state.users.isEmpty()  -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ShimmerScreenSearch()
            }
        }

        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ShimmerScreenSearch()
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 45.dp, bottom = 70.dp)
                  //  .verticalScroll(rememberScrollState())
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery=it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp,end=10.dp,bottom=20.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(
                            shape = RoundedCornerShape(30.dp),
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                        ),
                    shape = RoundedCornerShape(30.dp),
                    placeholder = {
                        Text(
                            text = "Search",
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .size(25.dp),
                            contentDescription = "Search Icon"
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        cursorColor = orange,
                        focusedContainerColor = Color.Gray.copy(alpha = 0.3f),
                        unfocusedContainerColor = Color.Gray.copy(alpha = 0.3f),
                        disabledContainerColor = Color.Gray.copy(alpha = 0.2f)
                    )
                )
                Text(
                    text = "Recommended",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                    modifier = Modifier.padding(start = 10.dp, bottom = 15.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())

                ) {

                    CategoryRow(
                        users = state.users,
                        categoryName = "#Trending",
                        modifier = Modifier.padding(start = 10.dp, bottom = 8.dp),
                        profileIconSize = 30.dp

                    )
                    CategoryRow(
                        users = state.users,
                        categoryName = "#Featured",
                        modifier = Modifier.padding(start = 10.dp, bottom = 8.dp),
                        profileIconSize = 30.dp
                    )
                    CategoryRow(
                        users = state.users,
                        categoryName = "#Fashion",
                        modifier = Modifier.padding(start = 10.dp, bottom = 8.dp),
                        profileIconSize = 30.dp
                    )
                }
            }
        }
    }
}