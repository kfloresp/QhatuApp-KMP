package com.rgk.qhatu.feature.sale.presentation.saledetail

import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation

sealed class SaleUiState {
    data class Success(val result: SaleWithOperation) : SaleUiState()
    object Loading : SaleUiState()
    data class Error(val message: String) : SaleUiState()
}