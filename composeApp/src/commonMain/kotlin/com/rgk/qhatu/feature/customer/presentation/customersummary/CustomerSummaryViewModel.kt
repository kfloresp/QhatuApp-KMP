package com.rgk.qhatu.feature.customer.presentation.customersummary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersUseCase
import com.rgk.qhatu.feature.customer.presentation.customerprofile.CustomerProfileDestination
import com.rgk.qhatu.feature.customer.presentation.customerprofile.CustomerProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerSummaryViewModel(
    private val getCustomersUseCase: GetCustomersUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CustomerSummaryUiState>(CustomerSummaryUiState.Loading)
    val uiState: StateFlow<CustomerSummaryUiState> = _uiState.asStateFlow()

    private val _customerProfileUiState = MutableStateFlow<CustomerProfileUiState>(CustomerProfileUiState.Loading)
    val customerProfileUiState: StateFlow<CustomerProfileUiState> = _customerProfileUiState.asStateFlow()


    private val destinationArgs = savedStateHandle.toRoute<CustomerSummaryDestination>()
    val idCustomer get():String? = destinationArgs.idCustomer

    init {
        loadInit()
    }

    fun loadInit() {
        idCustomer?.let {
            loadCustomer(it)
            loadSummary(it)
        }
    }

    private fun loadCustomer(idCustomer: String) {
        viewModelScope.launch {
            _customerProfileUiState.update {
                CustomerProfileUiState.Loading
            }
            val result = getCustomersUseCase(idCustomer)
            when (result) {
                is SyncResult.Error -> {
                    _customerProfileUiState.update {
                        CustomerProfileUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<List<Customer>> -> {
                    val customer = result.data.first()
                    _customerProfileUiState.update {
                        CustomerProfileUiState.Success(
                            result = customer
                        )
                    }
                }
            }
        }
    }

    private fun loadSummary(idCustomer: String) {
        viewModelScope.launch {
            _uiState.update {
                CustomerSummaryUiState.Loading
            }
//            val result = getCustomerSummaryUseCase(idCustomer)
//            when (result) {
//                is SyncResult.Error -> {
//                    _uiState.update {
//                        CustomerSummaryUiState.Error(result.exception.message.orEmpty())
//                    }
//                }
//
//                is SyncResult.Success<List<String>> -> {
//                    val result = result.data
//                    _uiState.update {
//                        CustomerSummaryUiState.Success(
//                            result = result
//                        )
//                    }
//                }
//            }
        }
    }

}