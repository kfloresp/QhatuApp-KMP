package com.rgk.qhatu.ui.feature.customer.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.ui.feature.customer.CustomerScreen
import kotlinx.serialization.Serializable

@Serializable
data object CustomerDestination

internal fun NavGraphBuilder.customerDestination(
) {
    composable<CustomerDestination> {
        CustomerScreen()
    }
}