package com.rgk.qhatu.feature.customer.presentation.customerform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.SyncCustomerUseCase
import com.rgk.qhatu.feature.customer.presentation.customerprofile.CustomerProfileDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerFormViewModel(
    private val getCustomersUseCase: GetCustomersUseCase,
    private val syncCustomerUseCase: SyncCustomerUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<CustomerFormUiState>(CustomerFormUiState.Loading)
    val uiState: StateFlow<CustomerFormUiState> = _uiState.asStateFlow()

    private val _isNewCustomer =
        MutableStateFlow(false)
    val isNewCustomer: StateFlow<Boolean> = _isNewCustomer.asStateFlow()

    private val destinationArgs = savedStateHandle.toRoute< CustomerProfileDestination>()
    val idCustomer get():String? = destinationArgs.idCustomer

    init {
        idCustomer?.let {
            loadCustomer(it)
        }
    }

    private fun loadCustomer(idCustomer: String) {
        viewModelScope.launch {
            _uiState.update {
                CustomerFormUiState.Loading
            }
            val result = getCustomersUseCase(idCustomer)
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CustomerFormUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<List<Customer>> -> {
                    val customer = result.data.first()
                    _uiState.update {
                        CustomerFormUiState.Success(
                            result = customer
                        )
                    }
                }
            }
        }
    }

    fun onUpsertLocal(it: Customer) {
        viewModelScope.launch {
            _uiState.update {
                CustomerFormUiState.Loading
            }
            val result = syncCustomerUseCase(SyncOperation.UpsertLocal(it))
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CustomerFormUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _uiState.update {
                        CustomerFormUiState.SuccessUpsert
                    }
                }
            }
        }
    }
}