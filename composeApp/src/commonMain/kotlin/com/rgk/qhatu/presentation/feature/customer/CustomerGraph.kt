package com.rgk.qhatu.presentation.feature.customer

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.presentation.feature.customer.ui.customer.CustomerDestination
import com.rgk.qhatu.presentation.feature.customer.ui.customer.customerDestination
import kotlinx.serialization.Serializable

@Serializable
data object CustomerGraph

fun NavController.navigateToCustomer(navOptions: NavOptions? = null) {
    navigate(CustomerGraph, navOptions)
}

fun NavGraphBuilder.customerGraph(
) {
    navigation<CustomerGraph>(
        startDestination = CustomerDestination
    ) {
        customerDestination()
    }
}