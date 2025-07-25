package com.rgk.qhatu.feature.payment.presentation.payment

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object PaymentDestination

internal fun NavGraphBuilder.paymentDestination(
) {
    composable<PaymentDestination> {
        PaymentScreen()
    }
}