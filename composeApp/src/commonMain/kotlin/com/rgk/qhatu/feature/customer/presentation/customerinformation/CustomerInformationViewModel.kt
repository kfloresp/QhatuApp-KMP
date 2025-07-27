package com.rgk.qhatu.feature.customer.presentation.customerinformation

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

class CustomerInformationViewModel(
    private val getCustomersUseCase: GetCustomersUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<CustomerInformationUiState>(CustomerInformationUiState.Loading)
    val uiState: StateFlow<CustomerInformationUiState> = _uiState.asStateFlow()

    private val _isNewCustomer =
        MutableStateFlow(false)
    val isNewCustomer: StateFlow<Boolean> = _isNewCustomer.asStateFlow()

    private val destinationArgs = savedStateHandle.toRoute<CustomerInformationDestination>()
    val idCustomer get():String? = destinationArgs.idCustomer

    init {
        idCustomer?.let {
            loadCustomer(it)
        } ?: run {
            _isNewCustomer.update {
                true
            }
            _uiState.update {
                CustomerInformationUiState.Success(Customer())
            }
        }
    }

    private fun loadCustomer(idCustomer: String) {
        viewModelScope.launch {
            _uiState.update {
                CustomerInformationUiState.Loading
            }
            val result = getCustomersUseCase(idCustomer)
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CustomerInformationUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<List<Customer>> -> {
                    val customer = result.data.first()
                    _uiState.update {
                        CustomerInformationUiState.Success(
                            result = customer
                        )
                    }
                }
            }
        }
    }
}