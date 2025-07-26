package com.rgk.qhatu.feature.customer.presentation.customerinformation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CustomerInformationDestination

internal fun NavGraphBuilder.customerInformationDestination(
) {
    composable<CustomerInformationDestination> {
        
    }
}