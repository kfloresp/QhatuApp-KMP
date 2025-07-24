package com.rgk.qhatu.feature.setting.presentation.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncType
import com.rgk.qhatu.feature.setting.presentation.sync.component.SyncUseCases
import kotlinx.coroutines.launch

class SyncViewModel(private val syncUseCases: SyncUseCases) : ViewModel() {

    fun sync(type: SyncType) {
        viewModelScope.launch {
            when (type) {
                SyncType.STORE -> syncUseCases.syncStore(SyncOperation.RemoteToLocal())
                SyncType.CATEGORY -> syncUseCases.syncCategory(SyncOperation.RemoteToLocal())
                SyncType.BRAND -> syncUseCases.syncBrand(SyncOperation.RemoteToLocal())
                SyncType.UNIT_MEASURE -> syncUseCases.syncUnitMeasure(SyncOperation.RemoteToLocal())
                SyncType.AUDIT -> syncUseCases.syncAuditLog(SyncOperation.RemoteToLocal())
                SyncType.CUSTOMER -> syncUseCases.syncCustomer(SyncOperation.RemoteToLocal())
                SyncType.PAYMENT_CUSTOMER -> syncUseCases.syncPaymentCustomer(SyncOperation.RemoteToLocal())
                SyncType.PAYMENT_TRANSACTION -> syncUseCases.syncPaymentTransaction(SyncOperation.RemoteToLocal())
                SyncType.PRODUCT -> syncUseCases.syncProduct(SyncOperation.RemoteToLocal())
                SyncType.SALE -> syncUseCases.syncSale(SyncOperation.RemoteToLocal())
                SyncType.SALE_DETAIL -> syncUseCases.syncSaleDetail(SyncOperation.RemoteToLocal())
                SyncType.CONFIGURATION -> syncUseCases.syncConfiguration(SyncOperation.RemoteToLocal())
            }
        }
    }

}