package com.rgk.qhatu.feature.customer.presentation.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.CustomerWithDetails
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val getCustomersUseCase: GetCustomersUseCase,
) : ViewModel() {
    private var allItems: List<CustomerWithDetails> = emptyList()
    private val _uiState = MutableStateFlow<CustomerUiState>(CustomerUiState.Loading)
    val uiState: StateFlow<CustomerUiState> = _uiState.asStateFlow()

    init {
        fetchLocal()
    }

    private fun fetchLocal() {
        viewModelScope.launch {
            _uiState.update {
                CustomerUiState.Loading
            }
            val result = getCustomersUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CustomerUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    allItems = result.data as List<CustomerWithDetails>
                    if (allItems.isNotEmpty()) {
                        _uiState.update {
                            CustomerUiState.Success(
                                result = allItems
                            )
                        }
                    } else {
                        _uiState.update {
                            CustomerUiState.Empty
                        }
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        if (_uiState.value !is CustomerUiState.Success) return

        val filtered = if (query.isBlank()) {
            allItems
        } else {
            allItems.filter { item ->
                when (item) {
                    is CustomerWithDetails.PersonWithCustomer -> {
                        item.person.firstName.contains(query, ignoreCase = true) ||
                                item.person.lastName.contains(query, ignoreCase = true)
                    }
                    is CustomerWithDetails.CompanyWithCustomer -> {
                        item.company.companyName.contains(query, ignoreCase = true)
                    }
                }
            }
        }

        _uiState.value = CustomerUiState.Success(
            result = filtered,
            query = query
        )
    }

    fun onItemClick(item: Customer) {
        if (_uiState.value is CustomerUiState.Loading) {
            return
        }
        _uiState.value = CustomerUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val result = syncCustomerUseCase(SyncOperation.UpsertLocal(item))
//                when (result) {
//                    is SyncResult.Error -> {
//                        _uiState.value = CustomerUiState.Error(result.exception.message.orEmpty())
//                    }
//
//                    is SyncResult.Success<*> -> {
//                        fetchLocal()
//                    }
//                }
//            } catch (e: Exception) {
//                _uiState.value = CustomerUiState.Error(e.message.orEmpty())
//            }
        }
    }

}