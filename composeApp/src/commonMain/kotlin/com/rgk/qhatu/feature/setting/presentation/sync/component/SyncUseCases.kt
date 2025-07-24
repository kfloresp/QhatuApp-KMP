package com.rgk.qhatu.feature.setting.presentation.sync.component

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

data class SyncUseCases(
    val syncStore: SyncStoreUseCase,
    val syncCategory: SyncCategoryUseCase,
    val syncBrand: SyncBrandUseCase,
    val syncUnitMeasure: SyncUnitMeasureUseCase,
    val syncAuditLog: SyncAuditLogUseCase,
    val syncCustomer: SyncCustomerUseCase,
    val syncPaymentCustomer: SyncPaymentTransactionUseCase,
    val syncPaymentTransaction: SyncPaymentTransactionUseCase,
    val syncProduct: SyncProductUseCase,
    val syncSale: SyncSaleUseCase,
    val syncSaleDetail: SyncSaleDetailUseCase,
    val syncConfiguration: SyncConfigurationUseCase
)