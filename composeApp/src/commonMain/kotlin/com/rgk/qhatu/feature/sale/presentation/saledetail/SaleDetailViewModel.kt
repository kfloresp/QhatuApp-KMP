package com.rgk.qhatu.feature.sale.presentation.saledetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SaleDetailViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow<SaleDetailUiState>(
        SaleDetailUiState.Loading)
    val uiState: StateFlow<SaleDetailUiState> = _uiState.asStateFlow()

}