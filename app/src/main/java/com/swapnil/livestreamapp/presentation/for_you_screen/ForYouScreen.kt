package com.swapnil.livestreamapp.presentation.for_you_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.swapnil.livestreamapp.presentation.for_you_screen.components.UserCard
import com.swapnil.livestreamapp.presentation.state.UserIntent

@Composable
fun ForYouScreen(viewModel: UserViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(UserIntent.LoadUsers)
    }

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Text("Error: ${state.error}", color = Color.Red)
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start=4.dp,end=4.dp, bottom = 70.dp),
                contentPadding = PaddingValues(2.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                itemsIndexed(
                    state.users,
                    key = { _, coin -> coin.fullName }) { index, user ->
                    val isVisible = remember {
                        derivedStateOf {
                            val visibleItems = listState.layoutInfo.visibleItemsInfo
                            visibleItems.any { it.index == index}
                        }
                    }
                    val scale = remember { Animatable(0f) }

                    // val hasAnimated = remember { mutableStateOf(false) }

                    LaunchedEffect(isVisible.value) {
                        if (isVisible.value) {
                            scale.animateTo(
                                targetValue = 1f,
                                animationSpec = tween(
                                    durationMillis = 300, // Adjust as needed for smoothness
                                    easing = FastOutSlowInEasing
                                )
                            )
                        } else {
                            scale.snapTo(0f) // Reset scale when not visible
                        }
                    }
                   UserCard(
                       modifier = Modifier
                           .height(250.dp) // ensures square shape
                           .fillMaxWidth()
                           .clip(RoundedCornerShape(4.dp))
                           .graphicsLayer(
                               scaleX = scale.value,
                               scaleY = scale.value
                           ),
                       imageUrl = user.imageUrl,
                       name = user.fullName
                   )
                }
            }
        }
    }
}

