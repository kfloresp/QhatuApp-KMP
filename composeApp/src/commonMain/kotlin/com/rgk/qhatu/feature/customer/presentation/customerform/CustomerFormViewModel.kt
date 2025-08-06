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

    private val _formState = MutableStateFlow(CustomerFormValidationState())
    val formState: StateFlow<CustomerFormValidationState> = _formState.asStateFlow()
    private val _isNewCustomer =
        MutableStateFlow(false)
    val isNewCustomer: StateFlow<Boolean> = _isNewCustomer.asStateFlow()

    private val destinationArgs = savedStateHandle.toRoute<CustomerFormDestination>()
    val idCustomer get():String = destinationArgs.idCustomer

    init {
        if (idCustomer.isEmpty()) {
            _isNewCustomer.value = true
            _uiState.value = CustomerFormUiState.Success(Customer())
            _formState.update {
                CustomerFormValidationState(Customer(),false)
            }
        } else {
            loadCustomer(idCustomer)
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
                    _formState.update {
                        CustomerFormValidationState(customer,false)
                    }
                }
            }
        }
    }

    fun onUpsertLocal(customer: Customer) {
        viewModelScope.launch {
            _uiState.update {
                CustomerFormUiState.Loading
            }
            val result = syncCustomerUseCase(SyncOperation.UpsertLocal(customer))
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CustomerFormUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _uiState.update {
                        CustomerFormUiState.SuccessUpsert(customer.isDeleted)
                    }
                }
            }
        }
    }

    fun onFieldChange(update: Customer.() -> Customer) {
        val currentFields = _formState.value.fields
        val updatedFields = currentFields.update()
        val isValid = validateFields(updatedFields)
        _formState.value = CustomerFormValidationState(updatedFields, isValid)
    }

    private fun validateFields(fields: Customer): Boolean {
        return fields.firstName.orEmpty().isNotBlank() &&
                fields.lastName.orEmpty().isNotBlank() &&
                fields.documentType.isNotBlank() &&
                fields.documentNumber.isNotBlank()
    }

}