package com.rgk.qhatu.feature.customer.presentation.customerprofile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomerWithDetailsByIdUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerProfileViewModel(
    private val getCustomerWithDetailsByIdUseCase: GetCustomerWithDetailsByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<CustomerProfileUiState>(CustomerProfileUiState.Loading)
    val uiState: StateFlow<CustomerProfileUiState> = _uiState.asStateFlow()

    private val destinationArgs = savedStateHandle.toRoute<CustomerProfileDestination>()
    val customerId get(): String = destinationArgs.customerId
    val documentType get(): String = destinationArgs.documentType

    init {
        if (customerId.isNotEmpty()) {
            loadCustomer(customerId)
        }
    }

    private fun loadCustomer(customerId: String) {
        viewModelScope.launch {
            _uiState.update {
                CustomerProfileUiState.Loading
            }
            val result = getCustomerWithDetailsByIdUseCase(customerId, documentType)
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CustomerProfileUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success -> {
                    _uiState.update {
                        CustomerProfileUiState.Success(result.data)
                    }
                }
            }
        }
    }
}