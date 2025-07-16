package com.rgk.qhatu.di

import com.rgk.qhatu.domain.usecase.AuthUseCase
import com.rgk.qhatu.domain.usecase.auditLog.GetAuditLogStatsUseCase
import com.rgk.qhatu.domain.usecase.auditLog.SyncAuditLogUseCase
import com.rgk.qhatu.domain.usecase.brand.GetBrandStatsUseCase
import com.rgk.qhatu.domain.usecase.brand.SyncBrandUseCase
import com.rgk.qhatu.domain.usecase.category.GetCategoryStatsUseCase
import com.rgk.qhatu.domain.usecase.category.SyncCategoryUseCase
import com.rgk.qhatu.domain.usecase.client.GetClientStatsUseCase
import com.rgk.qhatu.domain.usecase.client.GetClientUseCase
import com.rgk.qhatu.domain.usecase.client.GetProviderUseCase
import com.rgk.qhatu.domain.usecase.client.SyncClientUseCase
import com.rgk.qhatu.domain.usecase.clientPayment.GetClientPaymentStatsUseCase
import com.rgk.qhatu.domain.usecase.clientPayment.SyncClientPaymentUseCase
import com.rgk.qhatu.domain.usecase.configuration.GetConfigurationStatsUseCase
import com.rgk.qhatu.domain.usecase.configuration.SyncConfigurationUseCase
import com.rgk.qhatu.domain.usecase.paymentTransaction.GetPaymentTransactionStatsUseCase
import com.rgk.qhatu.domain.usecase.paymentTransaction.SyncPaymentTransactionUseCase
import com.rgk.qhatu.domain.usecase.product.GetProductFromQueryUseCase
import com.rgk.qhatu.domain.usecase.product.GetProductStatsUseCase
import com.rgk.qhatu.domain.usecase.product.SyncProductUseCase
import com.rgk.qhatu.domain.usecase.transaction.GetTransactionStatsUseCase
import com.rgk.qhatu.domain.usecase.transaction.SyncTransactionUseCase
import com.rgk.qhatu.domain.usecase.transactionDetail.GetTransactionDetailStatsUseCase
import com.rgk.qhatu.domain.usecase.transactionDetail.SyncTransactionDetailUseCase
import com.rgk.qhatu.domain.usecase.unitMeasure.GetUnitMeasureStatsUseCase
import com.rgk.qhatu.domain.usecase.unitMeasure.SyncUnitMeasureUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factory<AuthUseCase> { AuthUseCase(get()) }
    factoryOf(::GetAuditLogStatsUseCase)
    factoryOf(::GetBrandStatsUseCase)
    factoryOf(::GetCategoryStatsUseCase)
    factoryOf(::GetClientStatsUseCase)
    factoryOf(::GetClientPaymentStatsUseCase)
    factoryOf(::GetConfigurationStatsUseCase)
    factoryOf(::GetPaymentTransactionStatsUseCase)
    factoryOf(::GetProductStatsUseCase)
    factoryOf(::GetTransactionDetailStatsUseCase)
    factoryOf(::GetTransactionStatsUseCase)
    factoryOf(::GetUnitMeasureStatsUseCase)

    factoryOf(::SyncAuditLogUseCase)
    factoryOf(::SyncBrandUseCase)
    factoryOf(::SyncCategoryUseCase)
    factoryOf(::SyncClientUseCase)
    factoryOf(::SyncClientPaymentUseCase)
    factoryOf(::SyncConfigurationUseCase)
    factoryOf(::SyncPaymentTransactionUseCase)
    factoryOf(::SyncProductUseCase)
    factoryOf(::SyncTransactionUseCase)
    factoryOf(::SyncTransactionDetailUseCase)
    factoryOf(::SyncUnitMeasureUseCase)
    factoryOf(::GetProductFromQueryUseCase)
    factoryOf(::GetClientUseCase)
    factoryOf(::GetProviderUseCase)
}