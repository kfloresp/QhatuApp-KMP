package com.rgk.qhatu.common.components.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.shimmer.ShimmerWrapper

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShimmerChipsSkeleton(
    modifier: Modifier = Modifier,
    count: Int = 20,
    chipHeight: Dp = 32.dp
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(count) { index ->
            val randomWidth = remember(index) {
                (60..120).random().dp
            }

            ShimmerWrapper {
                Box(
                    modifier = it
                        .height(chipHeight)
                        .width(randomWidth)
                        .clip(RoundedCornerShape(50))
                )
            }
        }
    }
}
