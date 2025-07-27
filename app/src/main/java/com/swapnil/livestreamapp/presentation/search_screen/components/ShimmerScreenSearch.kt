package com.swapnil.livestreamapp.presentation.search_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.swapnil.livestreamapp.presentation.common_components.ShimmerBrush

@Composable
fun ShimmerScreenSearch() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp, bottom = 70.dp)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(
                    ShimmerBrush()
                )
        )
        Box(
            Modifier
                .width(100.dp)
                .height(30.dp)
                .padding(start = 10.dp, bottom = 15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(ShimmerBrush())
        )

        Column(
            Modifier.padding(start = 10.dp, bottom = 8.dp)
        ) {
            ShimmerCategoryRow()
            ShimmerCategoryRow()
            ShimmerCategoryRow()
        }
    }
}

@Composable
fun ShimmerCategoryRow(modifier: Modifier = Modifier) {
    Box(modifier=Modifier
        .width(50.dp)
        .height(20.dp).padding(bottom=10.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(ShimmerBrush()))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 10.dp)
    ) {
        items(10) { // number of placeholders
            Box(
                modifier = Modifier
                    .height(180.dp) // ensures square shape
                    .width(150.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(ShimmerBrush())
            )
        }
    }
}