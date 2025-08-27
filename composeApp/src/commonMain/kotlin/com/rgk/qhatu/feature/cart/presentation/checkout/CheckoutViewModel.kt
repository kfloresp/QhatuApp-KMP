package com.rgk.qhatu.feature.cart.presentation.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.domain.usecase.GetRefreshCartSummaryUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartItemsUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartSummaryUseCase
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.model.GENERIC_CUSTOMER
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersWithDebtUseCase
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.usecase.GetProductByIdUseCase
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation
import com.rgk.qhatu.feature.sale.domain.usecase.SaveSaleWithDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val getCustomerWithDebtsUseCase: GetCustomersWithDebtUseCase,
    private val observeCartItemsUseCase: ObserveCartItemsUseCase,
    private val getRefreshCartSummaryUseCase: GetRefreshCartSummaryUseCase,
    private val observeCartSummaryUseCase: ObserveCartSummaryUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val saveSaleWithDetailsUseCase: SaveSaleWithDetailsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CheckoutUiState>(CheckoutUiState.Loading)
    val uiState: StateFlow<CheckoutUiState> = _uiState.asStateFlow()

    private val _formState = MutableStateFlow(CheckoutFormValidationState())
    val formState: StateFlow<CheckoutFormValidationState> = _formState.asStateFlow()

    private val _customerList = MutableStateFlow<List<Customer>>(emptyList())
    val customerList: StateFlow<List<Customer>> = _customerList.asStateFlow()

    private var allItemsCustomer: List<Customer> = emptyList()

    private val _cartSummary = MutableStateFlow<CartSummary?>(null)
    val cartSummary: StateFlow<CartSummary?> = _cartSummary.asStateFlow()


    init {
        observeCartItems()
        refreshCartSummary()
    }

    fun onFieldChange(update: SaleWithOperation.() -> SaleWithOperation) {
        val currentFields = _formState.value.fields
        val updatedFields = currentFields.update()
        val isValid = validateFields(updatedFields)
        _formState.value = CheckoutFormValidationState(updatedFields, isValid)
    }

    private fun validateFields(fields: SaleWithOperation): Boolean {
        return fields.sale.customerId.isNotBlank() &&
                fields.details.isNotEmpty()
    }

    fun searchCustomer() {
        viewModelScope.launch {
            val result = getCustomerWithDebtsUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _customerList.value = listOf(GENERIC_CUSTOMER)
                }

                is SyncResult.Success<List<Customer>> -> {
                    _customerList.value = listOf(GENERIC_CUSTOMER) + result.data
                    allItemsCustomer = result.data
                }
            }
        }
    }

    fun onSearchCustomer(query: String) {
        val filtered = if (query.isBlank()) allItemsCustomer
        else allItemsCustomer.filter { it.nameCustomer.contains(query, ignoreCase = true) }
        _customerList.value = filtered
    }

    private fun refreshCartSummary() {
        viewModelScope.launch {
            val result = getRefreshCartSummaryUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CheckoutUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    observeCartSummary()
                }
            }
        }
    }

    private fun observeCartSummary() {
        viewModelScope.launch {
            observeCartSummaryUseCase()
                .filterNotNull()
                .distinctUntilChanged()
                .collect { summary ->
                    _cartSummary.value = summary
                }
        }
    }

    private fun observeCartItems() {
        viewModelScope.launch {
            observeCartItemsUseCase()
                .filterNotNull()
                .distinctUntilChanged()
                .collect { items ->
                    val productItems: List<Product> = items.mapNotNull { cartItem ->
                        getProductById(cartItem.productId)?.copy(cartItem = cartItem)
                    }

                    if (productItems.isNotEmpty()) {
                        _uiState.update {
                            CheckoutUiState.Success(result = productItems)
                        }
                    } else {
                        _uiState.update { CheckoutUiState.Empty }
                    }
                }
        }
    }

    private suspend fun getProductById(productId: String): Product? {
        val result = getProductByIdUseCase(productId)
        var product: Product? = null
        if (result is SyncResult.Success<Product?>) {
            product = result.data
        }
        return product
    }

    fun saveCheckout() {
        viewModelScope.launch {
            val result = saveSaleWithDetailsUseCase(_formState.value.fields)
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        CheckoutUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _uiState.update {
                        CheckoutUiState.SuccessSave
                    }
                }
            }
        }
    }

}