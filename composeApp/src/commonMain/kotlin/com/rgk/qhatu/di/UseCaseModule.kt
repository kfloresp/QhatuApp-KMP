package com.rgk.qhatu.di

import com.rgk.qhatu.feature.audit.domain.usecase.SyncAuditLogUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.AuthUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.LogoutUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.ObserveCurrentUser
import com.rgk.qhatu.feature.auth.domain.usecase.RegisterUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.AddItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.GetCartTotalUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartTotalUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.RemoveItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.UpdateItemToCartUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomerSummaryUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersWithDebtUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.SyncCustomerUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.GetPaymentsMethodUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.GetPaymentsUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.SyncPaymentUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.SyncPaymentTransactionUseCase
import com.rgk.qhatu.feature.product.domain.usecase.GetProductsUseCase
import com.rgk.qhatu.feature.product.domain.usecase.GetStorageTypeUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SaveImageProductUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SyncProductUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.SyncSaleDetailUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.SyncSaleUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetBrandsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoriesUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetStoreUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetUnitsMeasureUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncBrandUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncCategoryUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncConfigurationUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncStoreUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.SyncUnitMeasureUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factory<AuthUseCase> { AuthUseCase(get()) }
    factoryOf(::SyncAuditLogUseCase)
    factoryOf(::SyncBrandUseCase)
    factoryOf(::SyncCategoryUseCase)
    factoryOf(::SyncCustomerUseCase)
    factoryOf(::SyncPaymentUseCase)
    factoryOf(::SyncConfigurationUseCase)
    factoryOf(::SyncPaymentTransactionUseCase)
    factoryOf(::SyncProductUseCase)
    factoryOf(::SyncSaleUseCase)
    factoryOf(::SyncSaleDetailUseCase)
    factoryOf(::SyncUnitMeasureUseCase)
    factoryOf(::SyncStoreUseCase)
    factoryOf(::GetCategoriesUseCase)
    factoryOf(::GetUnitsMeasureUseCase)
    factoryOf(::GetBrandsUseCase)
    factoryOf(::GetStoreUseCase)
    factoryOf(::GetCustomersUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::ObserveCurrentUser)
    factoryOf(::LogoutUseCase)
    factoryOf(::GetCustomerSummaryUseCase)
    factoryOf(::GetPaymentsUseCase)
    factoryOf(::GetPaymentsMethodUseCase)
    factoryOf(::GetCustomersWithDebtUseCase)
    factoryOf(::GetProductsUseCase)
    factoryOf(::GetStorageTypeUseCase)
    factoryOf(::SaveImageProductUseCase)
    factoryOf(::ObserveCartTotalUseCase)
    factoryOf(::AddItemToCartUseCase)
    factoryOf(::RemoveItemToCartUseCase)
    factoryOf(::UpdateItemToCartUseCase)
    factoryOf(::GetCartTotalUseCase)

}