package com.rgk.qhatu.feature.customer.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.rgk.qhatu.common.extension.navigateToHomeWithPopUp
import com.rgk.qhatu.feature.customer.domain.model.DocumentType
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerDestination
import com.rgk.qhatu.feature.customer.presentation.customer.customerDestination
import com.rgk.qhatu.feature.customer.presentation.customerform.CustomerFormDestination
import com.rgk.qhatu.feature.customer.presentation.customerform.customerFormDestination
import com.rgk.qhatu.feature.customer.presentation.customerprofile.CustomerProfileDestination
import com.rgk.qhatu.feature.customer.presentation.customerprofile.customerProfileDestination
import com.rgk.qhatu.feature.customer.presentation.customersummary.CustomerSummaryDestination
import com.rgk.qhatu.feature.customer.presentation.customersummary.customerSummaryDestination
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
            onCustomerClick = { customerId, documentType ->
                navController.navigate(CustomerProfileDestination(customerId, documentType))
            }, onNewCustomerClick = {
                navController.navigate(CustomerFormDestination("", DocumentType.DNI.value))
            },
            onBackPopUp = {
                navController.navigateToHomeWithPopUp()
            }
        )
        customerProfileDestination(
            onResumeClick = {
                navController.navigate(CustomerSummaryDestination(it))
            }, onEditClick = { customerId, documentType ->
                navController.navigate(CustomerFormDestination(customerId, documentType.value))
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
                    navController.popBackStack() //revisar o proponer alternativa para recargar perfil
                }
            },
            onDeletePopUp = {
                navController.navigate(CustomerDestination)
            }
        )
        customerSummaryDestination()
    }
}