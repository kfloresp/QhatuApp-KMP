package com.rgk.qhatu.feature.customer.presentation.customerprofile

import androidx.compose.runtime.Composable
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.shimmer.ShimmerListVertical
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.presentation.customerprofile.component.ProfileCustomer

@Composable
fun CustomerProfileScreen(
    uiState: CustomerProfileUiState,
    onResumeClick: (Customer) -> Unit,
    onEditClick: (Customer) -> Unit,
) {
    when (uiState) {
        is CustomerProfileUiState.Error -> {
            ErrorSection(uiState.message)
        }

        CustomerProfileUiState.Loading -> {
            ShimmerListVertical()
        }

        is CustomerProfileUiState.Success -> {
            val result: Customer = uiState.result
            ProfileCustomer(
                customer = result,
                onEditClick = { onEditClick(result) },
                onResumeClick = {onResumeClick(result)},
            )
        }
    }
}