package com.rgk.qhatu.feature.product.presentation.productoform

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductFormViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow<ProductFormUiState>(ProductFormUiState.Loading)
    val uiState: StateFlow<ProductFormUiState> = _uiState.asStateFlow()
}