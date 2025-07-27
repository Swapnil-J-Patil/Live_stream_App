package com.swapnil.livestreamapp.presentation.profile_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraEnhance
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swapnil.livestreamapp.R
import com.swapnil.livestreamapp.presentation.common_components.UserCard
import com.swapnil.livestreamapp.presentation.profile_screen.components.CounterColumn
import com.swapnil.livestreamapp.presentation.profile_screen.components.ShimmerProfileScreen
import com.swapnil.livestreamapp.presentation.profile_screen.components.animateCounter
import com.swapnil.livestreamapp.presentation.search_screen.components.ShimmerScreenSearch
import com.swapnil.livestreamapp.presentation.state.UserIntent
import com.swapnil.livestreamapp.presentation.ui.theme.white

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val state by profileScreenViewModel.state.collectAsState()
    val listState = rememberLazyGridState()
    val earned = animateCounter(target = 34)
    val followers = animateCounter(target = 260)
    val following = animateCounter(target = 260)
    var isOfferVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        profileScreenViewModel.handleIntent(UserIntent.LoadUsers)
    }

    when {
        state.isLoading|| state.users.isEmpty()  -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ShimmerProfileScreen()
            }
        }

        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ShimmerProfileScreen()
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 45.dp, bottom = 70.dp, start = 10.dp, end = 10.dp)
                //  .verticalScroll(rememberScrollState())
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {

                    Text(
                        text = "Swapnil_Patil333",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .weight(0.7f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Row(
                        modifier = Modifier.weight(0.3f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraEnhance,
                            contentDescription = "Add photo",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(30.dp) // slightly bigger container
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                    // .height(50.dp)
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .size(100.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_img),
                                contentDescription = "profile_img",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape),
                            )
                            Box(
                                modifier = Modifier
                                    .padding(end = 5.dp, bottom = 5.dp),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PhotoCamera,
                                    contentDescription = "update_img",
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .background(
                                            shape = CircleShape,
                                            color = MaterialTheme.colorScheme.primaryContainer
                                        )
                                        .clip(CircleShape)
                                        .border(
                                            width = 1.dp,
                                            color = MaterialTheme.colorScheme.secondary,
                                            shape = CircleShape
                                        )
                                        .padding(5.dp)


                                )
                            }
                        }
                        Spacer(Modifier.width(15.dp))
                        Column(
                            modifier = Modifier.padding(vertical = 5.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Swapnil Patil",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Row(
                                modifier = Modifier.padding(top = 10.dp)
                            ) {
                                CounterColumn(title = earned, label = "Earned", iconRes = R.drawable.diamond)
                                Spacer(modifier = Modifier.width(20.dp))
                                CounterColumn(title = followers, label = "Followers")
                                Spacer(modifier = Modifier.width(20.dp))
                                CounterColumn(title = following, label = "Following")
                            }

                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Ensures proper spacing
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f), // Ensures both buttons take equal width
                        //.background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(8.dp))
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {

                        },
                        contentPadding = PaddingValues(vertical = 8.dp),
                    ) {
                        Text(
                            text = "Edit profile",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Button(
                        modifier = Modifier
                            .weight(1f), // Ensures both buttons take equal width
                        // .background(MaterialTheme.colorScheme.tertiaryContainer, RoundedCornerShape(8.dp))
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {

                        },
                        contentPadding = PaddingValues(vertical = 8.dp),
                    ) {
                        Text(
                            text = "Share profile",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                AnimatedVisibility(
                    visible = isOfferVisible,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = MaterialTheme.colorScheme.tertiary,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = "Earn",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.width(4.dp))

                            Image(
                                painter = painterResource(id = R.drawable.diamond),
                                contentDescription = "diamond",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "500 more to redeem $15",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Go Live",
                                style = MaterialTheme.typography.titleSmall,
                                textAlign = TextAlign.Center,
                                color = white,
                                modifier = Modifier
                                    .width(80.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        shape = RoundedCornerShape(8.dp), brush =
                                            Brush.verticalGradient(
                                                listOf(Color(0xFFE0331A), Color(0xFFE45F0F)),
                                            )

                                    )
                                    .padding(6.dp)

                            )
                        }
                        Spacer(Modifier.width(5.dp))

                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close coin",
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier

                                .size(20.dp)
                                .clip(CircleShape)
                                .clickable {
                                    isOfferVisible=false
                                }
                                .background(
                                    color = MaterialTheme.colorScheme.tertiaryContainer.copy(
                                        alpha = 0.5f
                                    ), shape = CircleShape
                                )
                                .padding(3.dp)

                        )

                    }
                }
                Spacer(Modifier.height(10.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize(),
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
                                .height(120.dp) // ensures square shape
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(4.dp))
                                .graphicsLayer(
                                    scaleX = scale.value,
                                    scaleY = scale.value
                                ),
                            imageUrl = user.imageUrl,
                            name = user.fullName,
                            isProfileScreen = true
                        )
                    }
                }
            }
        }
    }
}