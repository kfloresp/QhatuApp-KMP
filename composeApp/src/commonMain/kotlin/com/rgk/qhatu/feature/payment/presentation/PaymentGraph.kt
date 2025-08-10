package com.rgk.qhatu.feature.payment.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.common.extension.navigateToHomeWithPopUp
import com.rgk.qhatu.feature.payment.presentation.payment.PaymentDestination
import com.rgk.qhatu.feature.payment.presentation.payment.paymentDestination
import com.rgk.qhatu.feature.payment.presentation.paymentform.PaymentFormDestination
import com.rgk.qhatu.feature.payment.presentation.paymentform.paymentFormDestination
import kotlinx.serialization.Serializable

@Serializable
data object PaymentGraph

fun NavController.navigateToPaymentGraph(navOptions: NavOptions? = null) {
    navigate(PaymentGraph, navOptions)
}

fun NavGraphBuilder.paymentGraph(
    navController: NavController,
) {
    navigation<PaymentGraph>(
        startDestination = PaymentDestination
    ) {
        paymentDestination(
            onBackPopUp = { navController.navigateToHomeWithPopUp() },
            onPaymentClick = {
                navController.navigate(PaymentFormDestination(idPayment = it))
            },
            onNewPaymentClick = {
                navController.navigate(PaymentFormDestination(idPayment = ""))
            }
        )
        paymentFormDestination(
            onBackPopUp = {
                navController.navigate(PaymentDestination)
            },
            onDeletePopUp = {
                navController.navigate(PaymentDestination)
            }
        )
    }
}