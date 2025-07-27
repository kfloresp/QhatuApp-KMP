package com.rgk.qhatu.feature.customer.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerDestination
import com.rgk.qhatu.feature.customer.presentation.customer.customerDestination
import com.rgk.qhatu.feature.customer.presentation.customerinformation.CustomerInformationDestination
import com.rgk.qhatu.feature.customer.presentation.customerinformation.customerInformationDestination
import kotlinx.serialization.Serializable

@Serializable
data object CustomerGraph

fun NavController.navigateToCustomerGraph(navOptions: NavOptions? = null) {
    navigate(CustomerGraph, navOptions)
}

fun NavGraphBuilder.customerGraph(
    navController: NavController,
) {
    navigation<CustomerGraph>(
        startDestination = CustomerDestination
    ) {
        customerDestination(onCustomerClick = {
            navController.navigate(CustomerInformationDestination)
        })
        customerInformationDestination()
    }
}