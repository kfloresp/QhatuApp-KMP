package com.rgk.qhatu.presentation.feature.payment.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.presentation.feature.payment.PaymentScreen
import kotlinx.serialization.Serializable

@Serializable
data object PaymentDestination

internal fun NavGraphBuilder.paymentDestination(
) {
    composable<PaymentDestination> {
        PaymentScreen()
    }
}