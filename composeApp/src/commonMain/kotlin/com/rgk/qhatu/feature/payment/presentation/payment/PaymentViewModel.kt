package com.rgk.qhatu.feature.payment.presentation.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.payment.domain.model.Payment
import com.rgk.qhatu.feature.payment.domain.usecase.GetPaymentsUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.SyncPaymentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val syncPaymentUseCase: SyncPaymentUseCase,
    private val getPaymentsUseCase: GetPaymentsUseCase,
) : ViewModel() {
    private var allItems: List<Payment> = emptyList()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
    private val _uiState = MutableStateFlow<PaymentUiState>(PaymentUiState.Loading)
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    init {
        onPullRefresh()
    }

    fun onPullRefresh() {
        _isRefreshing.update { true }
        viewModelScope.launch {
            fetchLocal()
            _isRefreshing.update { false }
        }
    }

    private suspend fun fetchLocal() {
        _uiState.update {
            PaymentUiState.Loading
        }
        val result = getPaymentsUseCase()
        when (result) {
            is SyncResult.Error -> {
                _uiState.update {
                    PaymentUiState.Error(result.exception.message.orEmpty())
                }
            }

            is SyncResult.Success<List<Payment>> -> {
                allItems = result.data
                if (allItems.isNotEmpty()) {
                    _uiState.update {
                        PaymentUiState.Success(
                            result = allItems
                        )
                    }
                } else {
                    _uiState.update {
                        PaymentUiState.Empty
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        if (_uiState.value !is PaymentUiState.Success) return

        val filtered = if (query.isBlank()) allItems
        else allItems.filter { it.customer.contains(query, ignoreCase = true) }

        _uiState.value = PaymentUiState.Success(
            result = filtered,
            query = query
        )
    }

    fun onItemClick(item: Payment) {
        if (_uiState.value is PaymentUiState.Loading) {
            return
        }
        _uiState.value = PaymentUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncPaymentUseCase(SyncOperation.UpsertLocal(item))
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = PaymentUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = PaymentUiState.Error(e.message.orEmpty())
            }
        }
    }

    fun fetchRemote() {
        if (_uiState.value is PaymentUiState.Loading) {
            return
        }
        _uiState.value = PaymentUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = syncPaymentUseCase(SyncOperation.RemoteToLocal())
                when (result) {
                    is SyncResult.Error -> {
                        _uiState.value = PaymentUiState.Error(result.exception.message.orEmpty())
                    }

                    is SyncResult.Success<*> -> {
                        fetchLocal()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = PaymentUiState.Error(e.message.orEmpty())
            }
        }
    }
}