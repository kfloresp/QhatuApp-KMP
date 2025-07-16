package com.rgk.qhatu.presentation.feature.customer.ui.customer

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CustomerDestination

internal fun NavGraphBuilder.customerDestination(
) {
    composable<CustomerDestination> {
        CustomerScreen()
    }
}