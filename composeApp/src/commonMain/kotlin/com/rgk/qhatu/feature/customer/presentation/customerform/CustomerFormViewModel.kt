package com.rgk.qhatu.feature.customer.presentation.customerform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Company
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.model.DocumentType
import com.rgk.qhatu.feature.customer.domain.model.Person
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomerWithDetailsByIdUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.UpsertCustomerUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val DELAY_TIME = 500L

class CustomerFormViewModel(
    private val getCustomerWithDetailsByIdUseCase: GetCustomerWithDetailsByIdUseCase,
    private val upsertCustomerUseCase: UpsertCustomerUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _formUiState = MutableStateFlow<CustomerFormUiState>(CustomerFormUiState.Loading)
    val formUiState: StateFlow<CustomerFormUiState> = _formUiState.asStateFlow()

    private val _isNewCustomer = MutableStateFlow(false)
    val isNewCustomer: StateFlow<Boolean> = _isNewCustomer.asStateFlow()

    private val destinationArgs = savedStateHandle.toRoute<CustomerFormDestination>()
    val customerId get(): String = destinationArgs.customerId
    val documentType: DocumentType get() = DocumentType.fromValue(destinationArgs.documentType)

    init {
        if (customerId.isEmpty()) {
            val newCustomer = Customer(documentType = documentType)
            val details = when (documentType) {
                DocumentType.RUC -> {
                    CustomerWithDetails.CompanyWithCustomer(
                        Company(), newCustomer
                    )
                }

                DocumentType.DNI -> {
                    CustomerWithDetails.PersonWithCustomer(Person(), newCustomer)
                }

                DocumentType.PASSAPORT -> {
                    CustomerWithDetails.PersonWithCustomer(
                        Person(), newCustomer
                    )
                }
            }
            _formUiState.value = CustomerFormUiState.Upsert(details)
        } else {
            getCustomerWithDetails(customerId, documentType.value)
        }
    }

    private fun getCustomerWithDetails(customerId: String, documentType: String) {
        viewModelScope.launch {
            delay(DELAY_TIME)
            when (val result = getCustomerWithDetailsByIdUseCase(customerId, documentType)) {
                is SyncResult.Error -> {
                    _formUiState.value =
                        CustomerFormUiState.Error(result.exception.message.orEmpty())
                }

                is SyncResult.Success -> {
                    _formUiState.value = CustomerFormUiState.Upsert(result.data)
                }
            }
        }
    }

    fun onFieldChange(update: CustomerWithDetails.() -> CustomerWithDetails) {
        val current = _formUiState.value as? CustomerFormUiState.Upsert ?: return
        val updated = current.customerWithDetails.update()
        _formUiState.value = current.copy(
            customerWithDetails = updated, isValidForm = validate(updated)
        )
    }

    private fun validate(details: CustomerWithDetails): Boolean {
        return when (details) {
            is CustomerWithDetails.PersonWithCustomer -> details.person.firstName.isNotBlank() && details.person.lastName.isNotBlank() && details.customer.documentNumber.isNotBlank()

            is CustomerWithDetails.CompanyWithCustomer -> details.company.companyName.isNotBlank() && details.customer.documentNumber.isNotBlank()
        }
    }

    fun onUpsertLocal(customerWithDetails: CustomerWithDetails) {
        val current = _formUiState.value as? CustomerFormUiState.Upsert ?: return
        _formUiState.value = current.copy(isLoading = true)
        viewModelScope.launch {
            delay(DELAY_TIME)
            when (val result = upsertCustomerUseCase(customerWithDetails)) {
                is SyncResult.Error -> {
                    _formUiState.value =
                        CustomerFormUiState.Error(result.exception.message.orEmpty())
                }

                is SyncResult.Success<*> -> {
                    _formUiState.value = CustomerFormUiState.Success
                }
            }
        }
    }

}