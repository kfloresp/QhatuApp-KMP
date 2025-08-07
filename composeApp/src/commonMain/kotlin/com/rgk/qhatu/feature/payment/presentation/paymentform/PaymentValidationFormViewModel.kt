package com.rgk.qhatu.feature.payment.presentation.paymentform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.payment.domain.model.Payment
import com.rgk.qhatu.feature.payment.domain.usecase.GetPaymentsUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.SyncPaymentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentValidationFormViewModel(
    private val syncPaymentUseCase: SyncPaymentUseCase,
    private val getPaymentsUseCase: GetPaymentsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<PaymentFormUiState>(PaymentFormUiState.Loading)
    val uiState: StateFlow<PaymentFormUiState> = _uiState.asStateFlow()

    private val _formState = MutableStateFlow(PaymentFormValidationState())
    val formState: StateFlow<PaymentFormValidationState> = _formState.asStateFlow()
    private val _isNewPayment =
        MutableStateFlow(false)
    val isNewPayment: StateFlow<Boolean> = _isNewPayment.asStateFlow()

    private val destinationArgs = savedStateHandle.toRoute<PaymentFormDestination>()
    val idPayment get():String = destinationArgs.idPayment

    init {
        if (idPayment.isEmpty()) {
            _isNewPayment.value = true
            _uiState.value = PaymentFormUiState.Success(Payment())
            _formState.update {
                PaymentFormValidationState()
            }
        } else {
            loadCustomer(idPayment)
        }
    }

    private fun loadCustomer(idPayment: String) {
        viewModelScope.launch {
            _uiState.update {
                PaymentFormUiState.Loading
            }
            val result = getPaymentsUseCase(idPayment)
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        PaymentFormUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<List<Payment>> -> {
                    val payment = result.data.first()
                    _uiState.update {
                        PaymentFormUiState.Success(
                            result = payment
                        )
                    }
                    _formState.update {
                        PaymentFormValidationState(payment, false)
                    }
                }
            }
        }
    }

    fun onUpsertLocal(payment: Payment) {
        viewModelScope.launch {
            _uiState.update {
                PaymentFormUiState.Loading
            }
            val result = syncPaymentUseCase(SyncOperation.UpsertLocal(payment))
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        PaymentFormUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _uiState.update {
                        PaymentFormUiState.SuccessUpsert(payment.isDeleted)
                    }
                }
            }
        }
    }

    fun onFieldChange(update: Payment.() -> Payment) {
        val currentFields = _formState.value.fields
        val updatedFields = currentFields.update()
        val isValid = validateFields(updatedFields)
        _formState.value = PaymentFormValidationState(updatedFields, isValid)
    }

    private fun validateFields(fields: Payment): Boolean {
        return fields.paymentDate > 0 &&
                fields.amountPaid > 0 &&
                fields.clientId.isNotBlank() &&
                fields.paymentMethodId.isNotBlank()
    }
}