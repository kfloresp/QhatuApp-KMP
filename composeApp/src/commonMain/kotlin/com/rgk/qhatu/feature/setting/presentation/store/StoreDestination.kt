package com.rgk.qhatu.feature.setting.presentation.store

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object StoreDestination

internal fun NavGraphBuilder.storeDestination() {
    composable<StoreDestination> {
        StoreScreen()
    }
}