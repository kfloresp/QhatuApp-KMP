package com.rgk.qhatu.feature.search.presentation.search

import com.rgk.qhatu.feature.product.domain.model.Product

sealed class SearchUiState {
    data class Success(val result: List<Product>, val query: String = "") : SearchUiState()
    object Loading : SearchUiState()
    object Empty : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}