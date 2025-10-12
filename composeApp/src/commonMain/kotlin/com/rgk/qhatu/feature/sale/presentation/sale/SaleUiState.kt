package com.rgk.qhatu.feature.sale.presentation.sale

import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation

sealed class SaleUiState {
    data class Success(val result: List<SaleWithOperation>, val query: String = "") : SaleUiState()
    object Loading : SaleUiState()
    object Empty : SaleUiState()
    data class Error(val message: String) : SaleUiState()
}