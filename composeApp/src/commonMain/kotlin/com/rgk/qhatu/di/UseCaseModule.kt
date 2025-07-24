package com.rgk.qhatu.di

import com.rgk.qhatu.feature.audit.domain.usecase.GetAuditLogStatsUseCase
import com.rgk.qhatu.feature.audit.domain.usecase.SyncAuditLogUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.AuthUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.LogoutUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.ObserveCurrentUser
import com.rgk.qhatu.feature.auth.domain.usecase.RegisterUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetClientStatsUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetClientUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetProviderUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.SyncCustomerUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.GetClientPaymentStatsUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.SyncPaymentCustomerUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.GetPaymentTransactionStatsUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.SyncPaymentTransactionUseCase
import com.rgk.qhatu.feature.product.domain.usecase.GetProductFromQueryUseCase
import com.rgk.qhatu.feature.product.domain.usecase.GetProductStatsUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SyncProductUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.GetTransactionDetailStatsUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.GetTransactionStatsUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.SyncSaleDetailUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.SyncSaleUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetBrandStatsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetBrandsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoriesUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoryStatsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetConfigurationStatsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetUnitMeasureStatsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetUnitsMeasureUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncBrandUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncCategoryUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncConfigurationUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncUnitMeasureUseCase
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
    factoryOf(::SyncCustomerUseCase)
    factoryOf(::SyncPaymentCustomerUseCase)
    factoryOf(::SyncConfigurationUseCase)
    factoryOf(::SyncPaymentTransactionUseCase)
    factoryOf(::SyncProductUseCase)
    factoryOf(::SyncSaleUseCase)
    factoryOf(::SyncSaleDetailUseCase)
    factoryOf(::SyncUnitMeasureUseCase)
    factoryOf(::GetProductFromQueryUseCase)
    factoryOf(::GetClientUseCase)
    factoryOf(::GetProviderUseCase)
    factoryOf(::GetCategoriesUseCase)
    factoryOf(::GetUnitsMeasureUseCase)
    factoryOf(::GetBrandsUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::ObserveCurrentUser)
    factoryOf(::RegisterUseCase)

}