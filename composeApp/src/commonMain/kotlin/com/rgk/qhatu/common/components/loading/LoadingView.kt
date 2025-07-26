package com.rgk.qhatu.common.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_loading

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    text: String = stringResource(Res.string.tx_loading),
    icon: ImageVector? = null,
    textColor: Color = Color.White,
    indicatorColor: Color = Color.White,
    backgroundColor: Color = Color.Black.copy(alpha = 0.5f),
    indicatorSize: Dp = 48.dp,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            icon?.let {
                Icon(imageVector = it, contentDescription = null)
                Spacer(modifier = Modifier.height(16.dp))
            }
            CircularProgressIndicator(
                color = indicatorColor,
                strokeWidth = 4.dp,
                modifier = Modifier.size(indicatorSize)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}