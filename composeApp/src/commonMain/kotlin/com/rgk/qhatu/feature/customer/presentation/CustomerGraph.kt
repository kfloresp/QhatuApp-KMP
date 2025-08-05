package com.rgk.qhatu.feature.customer.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.common.extension.navigateToHomeWithPopUp
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerDestination
import com.rgk.qhatu.feature.customer.presentation.customer.customerDestination
import com.rgk.qhatu.feature.customer.presentation.customerform.CustomerFormDestination
import com.rgk.qhatu.feature.customer.presentation.customerform.customerFormDestination
import com.rgk.qhatu.feature.customer.presentation.customerprofile.CustomerProfileDestination
import com.rgk.qhatu.feature.customer.presentation.customerprofile.customerProfileDestination
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
        customerDestination(
            onCustomerClick = {
                navController.navigate(CustomerProfileDestination(it))
            }, onNewCustomerClick = {
                navController.navigate(CustomerFormDestination(""))
            },
            onBackPopUp = {
                navController.navigateToHomeWithPopUp()
            }
        )
        customerProfileDestination(
            onResumeClick = {
            }, onEditClick = {
                navController.navigate(CustomerFormDestination(it))
            },
            onBackPopUp = {
                navController.navigate(CustomerDestination)
            }
        )
        customerFormDestination(
            onBackPopUp = { idCustomer ->
                if (idCustomer.isEmpty()) {
                    navController.navigate(CustomerDestination)
                } else {
                    navController.navigate(CustomerProfileDestination(idCustomer))
                }
            },
            onDeletePopUp = {
                navController.navigate(CustomerDestination)
            }
        )
    }
}