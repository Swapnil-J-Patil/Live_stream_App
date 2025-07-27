package com.swapnil.livestreamapp.presentation.profile_screen.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun animateCounter(target: Int): String {
    var startAnimation by remember { mutableStateOf(false) }

    val animatedValue by animateIntAsState(
        targetValue = if (startAnimation) target else 1,
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
        label = ""
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    return animatedValue.toString()
}
