package com.rgk.qhatu.feature.customer.presentation.customersummary

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CustomerSummaryDestination

internal fun NavGraphBuilder.customerSummaryDestination(
) {
    composable<CustomerSummaryDestination> {

    }
}