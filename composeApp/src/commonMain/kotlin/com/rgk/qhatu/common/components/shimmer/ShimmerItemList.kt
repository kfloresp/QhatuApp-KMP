package com.rgk.qhatu.common.components.shimmer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

const val REPEAT_SHIMMER = 15

@Composable
fun ShimmerListVertical(repeat: Int = REPEAT_SHIMMER) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(repeat) {
            ShimmerItem()
        }
    }
}