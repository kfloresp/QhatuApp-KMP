package com.rgk.qhatu.feature.setting.presentation.sync

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SyncDestination

internal fun NavGraphBuilder.syncDestination() {
    composable<SyncDestination> {
        SyncScreen()
    }
}