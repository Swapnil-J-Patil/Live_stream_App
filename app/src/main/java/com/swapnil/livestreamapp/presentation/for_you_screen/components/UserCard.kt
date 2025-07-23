package com.swapnil.livestreamapp.presentation.for_you_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.swapnil.livestreamapp.R
import com.swapnil.livestreamapp.presentation.ui.theme.grey
import com.swapnil.livestreamapp.presentation.ui.theme.white

@Composable
fun UserCard(
    imageUrl: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier=Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = modifier
                    .background(Color.Transparent)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                // Profile Icon
                Box(
                    modifier = Modifier
                        .size(40.dp) // Adjust size as needed
                        .clip(CircleShape)
                        .background(grey.copy(alpha = 0.2f)), // Background color for the icon
                    contentAlignment = Alignment.Center // Center the icon inside the box
                ) {
                    AsyncImage(
                        ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .size(Size.ORIGINAL)
                            .build(),
                        contentDescription = "Profile Icon",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.width(2.dp)) // Space between icon and text

                // Text Column
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodySmall,
                        color = white,
                        maxLines = 1

                    )
                  //  Spacer(modifier = Modifier.height(2.dp))
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.diamond),
                            contentDescription = "diamond",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(20.dp)
                        )
                     //   Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "260",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = white,
                        )
                    }
                }
            }
        }
    }
}