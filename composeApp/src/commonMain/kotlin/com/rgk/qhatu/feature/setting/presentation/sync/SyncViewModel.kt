package com.rgk.qhatu.feature.setting.presentation.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.audit.domain.usecase.SyncAuditLogUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.SyncCustomerUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.SyncPaymentUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SyncProductUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncBrandUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncCategoryUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncConfigurationUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncStoreUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncUnitMeasureUseCase
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SyncViewModel(
    private val syncStore: SyncStoreUseCase,
    private val syncCategory: SyncCategoryUseCase,
    private val syncBrand: SyncBrandUseCase,
    private val syncUnitMeasure: SyncUnitMeasureUseCase,
    private val syncAuditLog: SyncAuditLogUseCase,
    private val syncCustomer: SyncCustomerUseCase,
    private val syncPayment: SyncPaymentUseCase,
    private val syncProduct: SyncProductUseCase,
    private val syncConfiguration: SyncConfigurationUseCase,
) : ViewModel() {
    private val DELAY_TIME = 500L
    private val _uiState = MutableStateFlow(
        SyncUiState(itemStates = List(SyncType.entries.size) { SyncItemState.Idle })
    )
    val uiState: StateFlow<SyncUiState> = _uiState

    private fun updateItemState(index: Int, newState: SyncItemState) {
        _uiState.update { currentState ->
            val newList = currentState.itemStates.toMutableList()
            newList[index] = newState
            currentState.copy(itemStates = newList)
        }
    }

    fun syncAll(){
        SyncType.entries.forEachIndexed { index, type ->
            sync(type, index)
        }
    }

    fun sync(type: SyncType, index: Int) {
        val currentState = uiState.value.itemStates.getOrNull(index)
        if (currentState is SyncItemState.Loading) return

        updateItemState(index, SyncItemState.Loading)
        viewModelScope.launch {
            delay(DELAY_TIME)
            val result = when (type) {
                SyncType.STORE -> {
                    syncStore(SyncOperation.RemoteToLocal())
                }

                SyncType.CATEGORY -> {
                    syncCategory(SyncOperation.RemoteToLocal())
                }

                SyncType.BRAND -> {
                    syncBrand(SyncOperation.RemoteToLocal())
                }

                SyncType.UNIT_MEASURE -> {
                    syncUnitMeasure(SyncOperation.RemoteToLocal())
                }

                SyncType.AUDIT -> {
                    syncAuditLog(SyncOperation.RemoteToLocal())
                }

                SyncType.CUSTOMER -> {
                    syncCustomer(SyncOperation.RemoteToLocal())
                }

                SyncType.PAYMENT_CUSTOMER -> {
                    syncPayment(SyncOperation.RemoteToLocal())
                }

                SyncType.PRODUCT -> {
                    syncProduct(SyncOperation.RemoteToLocal())
                }

                SyncType.SALE -> {
                }

                SyncType.SALE_DETAIL -> {
                }

                SyncType.CONFIGURATION -> {
                    syncConfiguration(SyncOperation.RemoteToLocal())
                }
            }
            when (result) {
                is SyncResult.Error -> {
                    updateItemState(
                        index,
                        SyncItemState.Error(message = result.exception.message.orEmpty())
                    )
                }

                is SyncResult.Success<*> -> {
                    updateItemState(index, SyncItemState.Success())
                }
            }
        }
    }

}