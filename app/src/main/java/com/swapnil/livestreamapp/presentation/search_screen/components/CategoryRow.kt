package com.swapnil.livestreamapp.presentation.search_screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.swapnil.livestreamapp.domain.model.User
import com.swapnil.livestreamapp.presentation.common_components.UserCard
import com.swapnil.livestreamapp.presentation.ui.theme.white

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier,
    users: List<User>,
    categoryName: String,
    profileIconSize: Dp = 40.dp
) {
    val listState = rememberLazyListState()

    Column(
        modifier = modifier
    ) {
        Text(
            text = categoryName,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = white,
            modifier=Modifier.padding(bottom=6.dp)
        )
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp)
        ) {
            itemsIndexed(
                users,
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
                        .height(180.dp) // ensures square shape
                        .width(150.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .graphicsLayer(
                            scaleX = scale.value,
                            scaleY = scale.value
                        ),
                    imageUrl = user.imageUrl,
                    name = user.fullName,
                    profileIconSize = profileIconSize
                )
            }
        }
    }

}