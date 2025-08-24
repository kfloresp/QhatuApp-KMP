package com.rgk.qhatu.feature.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.domain.usecase.GetRefreshCartSummaryUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartSummaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRefreshCartSummaryUseCase: GetRefreshCartSummaryUseCase,
    private val observeCartSummaryUseCase: ObserveCartSummaryUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _cartSummary = MutableStateFlow<CartSummary?>(null)
    val cartSummary: StateFlow<CartSummary?> = _cartSummary.asStateFlow()

    init {
        refreshCartSummary()
    }
    private fun refreshCartSummary() {
        viewModelScope.launch {
            val result = getRefreshCartSummaryUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        HomeUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _uiState.update { HomeUiState.Success }
                    observeCartSummary()
                }
            }
        }
    }

    private fun observeCartSummary() {
        viewModelScope.launch {
            observeCartSummaryUseCase()
                .filterNotNull()
                .distinctUntilChanged()
                .collect { summary ->
                    _cartSummary.value = summary
                }
        }
    }
}