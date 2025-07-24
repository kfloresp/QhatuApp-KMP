package com.rgk.qhatu.feature.setting.presentation.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.audit.domain.usecase.SyncAuditLogUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.SyncCustomerUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.SyncPaymentTransactionUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SyncProductUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.SyncSaleDetailUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.SyncSaleUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncBrandUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncCategoryUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncConfigurationUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncStoreUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncUnitMeasureUseCase
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SyncViewModel(
    private val syncStore: SyncStoreUseCase,
    private val syncCategory: SyncCategoryUseCase,
    private val syncBrand: SyncBrandUseCase,
    private val syncUnitMeasure: SyncUnitMeasureUseCase,
    private val syncAuditLog: SyncAuditLogUseCase,
    private val syncCustomer: SyncCustomerUseCase,
    private val syncPaymentCustomer: SyncPaymentTransactionUseCase,
    private val syncPaymentTransaction: SyncPaymentTransactionUseCase,
    private val syncProduct: SyncProductUseCase,
    private val syncSale: SyncSaleUseCase,
    private val syncSaleDetail: SyncSaleDetailUseCase,
    private val syncConfiguration: SyncConfigurationUseCase,
) : ViewModel() {
    private val DELAY_TIME = 500L
    private val _uiState = MutableStateFlow<SyncUiState>(SyncUiState.Idle)
    val uiState: StateFlow<SyncUiState> = _uiState.asStateFlow()

    fun sync(type: SyncType, index: Int) {
        _uiState.update {
            SyncUiState.Loading(index)
        }
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
                    syncPaymentCustomer(SyncOperation.RemoteToLocal())
                }

                SyncType.PAYMENT_TRANSACTION -> {
                    syncPaymentTransaction(SyncOperation.RemoteToLocal())
                }

                SyncType.PRODUCT -> {
                    syncProduct(SyncOperation.RemoteToLocal())
                }

                SyncType.SALE -> {
                    syncSale(SyncOperation.RemoteToLocal())
                }

                SyncType.SALE_DETAIL -> {
                    syncSaleDetail(SyncOperation.RemoteToLocal())
                }

                SyncType.CONFIGURATION -> {
                    syncConfiguration(SyncOperation.RemoteToLocal())
                }
            }
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        SyncUiState.Error(index, result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _uiState.update {
                        SyncUiState.Success(index)
                    }
                }
            }
        }
    }

}