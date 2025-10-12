package com.rgk.qhatu.feature.customer.presentation.customerprofile

import androidx.compose.runtime.Composable
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.shimmer.ShimmerListVertical
import com.rgk.qhatu.feature.customer.domain.model.DocumentType
import com.rgk.qhatu.feature.customer.presentation.customerprofile.component.ProfileCustomer

@Composable
fun CustomerProfileScreen(
    uiState: CustomerProfileUiState,
    onResumeClick: (String) -> Unit,
    onEditClick: (String, DocumentType) -> Unit,
) {
    when (uiState) {
        is CustomerProfileUiState.Error -> {
            ErrorSection(uiState.message)
        }

        CustomerProfileUiState.Loading -> {
            ShimmerListVertical()
        }

        is CustomerProfileUiState.Success -> {
            val result = uiState.result
            ProfileCustomer(
                customerWithDetails = result,
                onEditClick = { customerId, documentType ->
                    onEditClick(customerId, documentType)
                },
                onResumeClick = { onResumeClick(it) },
            )
        }
    }
}