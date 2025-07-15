package com.rgk.qhatu.ui.feature.payment.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.ui.feature.payment.PaymentScreen
import kotlinx.serialization.Serializable

@Serializable
data object PaymentDestination

internal fun NavGraphBuilder.paymentDestination(
) {
    composable<PaymentDestination> {
        PaymentScreen()
    }
}