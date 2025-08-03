package com.rgk.qhatu.feature.customer.presentation.customerprofile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerProfileViewModel(
    private val getCustomersUseCase: GetCustomersUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<CustomerProfileUiState>(CustomerProfileUiState.Loading)
    val uiState: StateFlow<CustomerProfileUiState> = _uiState.asStateFlow()

    private val destinationArgs = savedStateHandle.toRoute<CustomerProfileDestination>()
    val idCustomer get():String? = destinationArgs.idCustomer

    init {
        idCustomer?.let {
            loadCustomer(it)
        }
    }

    private fun loadCustomer(idCustomer: String) {
        viewModelScope.launch {
            _uiState.update {
                CustomerProfileUiState.Loading
            }
            val result = getCustomersUseCase(idCustomer)
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CustomerProfileUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<List<Customer>> -> {
                    val customer = result.data.first()
                    _uiState.update {
                        CustomerProfileUiState.Success(
                            result = customer
                        )
                    }
                }
            }
        }
    }
}