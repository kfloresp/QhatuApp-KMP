package com.rgk.qhatu.di

import com.rgk.qhatu.domain.feature.audit.usecase.GetAuditLogStatsUseCase
import com.rgk.qhatu.domain.feature.audit.usecase.SyncAuditLogUseCase
import com.rgk.qhatu.domain.feature.auth.usecase.AuthUseCase
import com.rgk.qhatu.domain.feature.setting.usecase.GetBrandStatsUseCase
import com.rgk.qhatu.domain.feature.setting.usecase.SyncBrandUseCase
import com.rgk.qhatu.domain.feature.setting.usecase.GetCategoryStatsUseCase
import com.rgk.qhatu.domain.feature.setting.usecase.SyncCategoryUseCase
import com.rgk.qhatu.domain.feature.customer.usecase.GetClientStatsUseCase
import com.rgk.qhatu.domain.feature.customer.usecase.GetClientUseCase
import com.rgk.qhatu.domain.feature.customer.usecase.GetProviderUseCase
import com.rgk.qhatu.domain.feature.customer.usecase.SyncClientUseCase
import com.rgk.qhatu.domain.feature.payment.usecase.GetClientPaymentStatsUseCase
import com.rgk.qhatu.domain.feature.payment.usecase.SyncClientPaymentUseCase
import com.rgk.qhatu.domain.feature.setting.usecase.GetConfigurationStatsUseCase
import com.rgk.qhatu.domain.feature.setting.usecase.SyncConfigurationUseCase
import com.rgk.qhatu.domain.feature.payment.usecase.GetPaymentTransactionStatsUseCase
import com.rgk.qhatu.domain.feature.payment.usecase.SyncPaymentTransactionUseCase
import com.rgk.qhatu.domain.feature.product.usecase.GetProductFromQueryUseCase
import com.rgk.qhatu.domain.feature.product.usecase.GetProductStatsUseCase
import com.rgk.qhatu.domain.feature.product.usecase.SyncProductUseCase
import com.rgk.qhatu.domain.feature.sale.usecase.GetTransactionDetailStatsUseCase
import com.rgk.qhatu.domain.feature.sale.usecase.GetTransactionStatsUseCase
import com.rgk.qhatu.domain.feature.sale.usecase.SyncTransactionDetailUseCase
import com.rgk.qhatu.domain.feature.sale.usecase.SyncTransactionUseCase
import com.rgk.qhatu.domain.feature.setting.usecase.GetUnitMeasureStatsUseCase
import com.rgk.qhatu.domain.feature.setting.usecase.SyncUnitMeasureUseCase
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