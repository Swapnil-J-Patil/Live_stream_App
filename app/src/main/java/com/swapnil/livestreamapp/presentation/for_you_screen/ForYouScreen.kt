package com.swapnil.livestreamapp.presentation.for_you_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swapnil.livestreamapp.R
import com.swapnil.livestreamapp.presentation.for_you_screen.components.ShimmerGrid
import com.swapnil.livestreamapp.presentation.for_you_screen.components.UserCard
import com.swapnil.livestreamapp.presentation.state.UserIntent
import com.swapnil.livestreamapp.presentation.ui.theme.white

@Composable
fun ForYouScreen(
    userViewModel: UserViewModel = hiltViewModel()
) {
    val state by userViewModel.state.collectAsState()
    val listState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        userViewModel.handleIntent(UserIntent.LoadUsers)
    }

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ShimmerGrid(columnCount = 2)
            }
        }

        state.error != null -> {
            Text("Error: ${state.error}", color = Color.Red)
        }

        else -> {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(top=45.dp, bottom = 70.dp)
            )  {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row (
                        modifier = Modifier.weight(0.5f)
                           ,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.rupee),
                            contentDescription = "rupee",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.size(15.dp)
                              //  .padding(end = 5.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "260",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = white,
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add coin",
                            modifier = Modifier.size(25.dp)
                                .clip(CircleShape)
                                .background(color = white, shape = CircleShape)
                                .padding(3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Go Live",
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier
                            .width(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(shape = RoundedCornerShape(12.dp), brush =
                                Brush.verticalGradient(
                                    listOf(Color(0xFFFF5722), Color(0xFFFF9800)),
                                )

                            )
                            .padding(8.dp)

                    )
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 4.dp, end = 4.dp),
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
                                visibleItems.any { it.index == index }
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
}

