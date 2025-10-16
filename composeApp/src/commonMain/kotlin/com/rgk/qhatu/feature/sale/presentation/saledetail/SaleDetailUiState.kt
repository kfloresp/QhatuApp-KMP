package com.rgk.qhatu.feature.sale.presentation.saledetail

import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation

sealed class SaleDetailUiState {
    data class Success(val result: SaleWithOperation) : SaleDetailUiState()
    object Loading : SaleDetailUiState()
    data class Error(val message: String) : SaleDetailUiState()
}