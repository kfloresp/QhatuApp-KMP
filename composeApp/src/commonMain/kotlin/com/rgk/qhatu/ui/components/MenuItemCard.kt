package com.rgk.qhatu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MenuItemCard(params: MenuItemCardParams) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            params.backgroundColor,
            params.backgroundColor.copy(alpha = 0.8f)
        ),
        start = Offset(0f,0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
    Card(
        modifier = params.modifier,
        shape = RoundedCornerShape(16.dp),
        onClick = params.onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = params.icon,
                    contentDescription = params.title,
                    modifier = Modifier.size(48.dp),
                    tint = params.iconTint
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = params.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = params.textColor
                )
            }
        }
    }
}

data class MenuItemCardParams(
    val title: String,
    val icon: ImageVector,
    val modifier: Modifier = Modifier,
    val backgroundColor: Color = Color.Black,
    val iconTint: Color = Color.White,
    val textColor: Color = Color.White,
    val onClick: () -> Unit
)
data class MenuItem(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val onClick: () -> Unit
)
