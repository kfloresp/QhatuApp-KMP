package com.rgk.qhatu.feature.sale.presentation.saledetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.presentation.customerform.CustomerFormDestination
import com.rgk.qhatu.feature.sale.domain.usecase.GetSaleWithDetailsById
import com.rgk.qhatu.feature.sale.presentation.sale.SaleUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SaleDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getSaleWithDetailsById: GetSaleWithDetailsById,
) : ViewModel() {
    private val _uiState = MutableStateFlow<SaleDetailUiState>(
        SaleDetailUiState.Loading
    )
    val uiState: StateFlow<SaleDetailUiState> = _uiState.asStateFlow()

    private val destinationArgs = savedStateHandle.toRoute<SaleDetailDestination>()
    val operationId get(): String = destinationArgs.operationId

    init {
        getSaleWithDetailById(operationId)
    }

    private fun getSaleWithDetailById(operationId: String) {
        viewModelScope.launch {
            _uiState.update {
                SaleDetailUiState.Loading
            }
            val result = getSaleWithDetailsById(operationId)
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        SaleDetailUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success -> {
                    result.data?.let { sale ->
                        _uiState.update {
                            SaleDetailUiState.Success(sale)
                        }
                    }
                }
            }
        }
    }
}