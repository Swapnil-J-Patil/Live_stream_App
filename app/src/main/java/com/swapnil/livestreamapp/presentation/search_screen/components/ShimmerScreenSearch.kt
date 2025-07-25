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
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(
                    ShimmerBrush()
                )
        )
        Box(
            Modifier
                .padding(start = 10.dp, bottom = 15.dp)
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
    Box(modifier=Modifier.padding(bottom=6.dp).background(ShimmerBrush()))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
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