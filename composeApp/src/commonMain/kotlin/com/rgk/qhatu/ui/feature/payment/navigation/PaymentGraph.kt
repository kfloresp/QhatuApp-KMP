package com.rgk.qhatu.ui.feature.payment.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object PaymentGraph

fun NavController.navigateToPayment(navOptions: NavOptions? = null) {
    navigate(PaymentGraph, navOptions)
}

fun NavGraphBuilder.paymentGraph(
) {
    navigation<PaymentGraph>(
        startDestination = PaymentDestination
    ) {
        paymentDestination()
    }
}