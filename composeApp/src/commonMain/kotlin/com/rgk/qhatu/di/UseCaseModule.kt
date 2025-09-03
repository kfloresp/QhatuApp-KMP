package com.rgk.qhatu.di

import com.rgk.qhatu.feature.audit.domain.usecase.SyncAuditLogUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.AuthUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.LogoutUseCase
import com.rgk.qhatu.feature.auth.domain.usecase.ObserveCurrentUser
import com.rgk.qhatu.feature.auth.domain.usecase.RegisterUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.AddItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.DeleteCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.GetCartsInactive
import com.rgk.qhatu.feature.cart.domain.usecase.GetRefreshCartSummaryUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartItemsUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ObserveCartSummaryUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.RemoveItemToCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.ResumeCartUseCase
import com.rgk.qhatu.feature.cart.domain.usecase.UpdateItemToCartUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomerSummaryUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.GetCustomersWithDebtUseCase
import com.rgk.qhatu.feature.customer.domain.usecase.SyncCustomerUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.GetPaymentsMethodUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.GetPaymentsUseCase
import com.rgk.qhatu.feature.payment.domain.usecase.SyncPaymentUseCase
import com.rgk.qhatu.feature.product.domain.usecase.GetAllProductsUseCase
import com.rgk.qhatu.feature.product.domain.usecase.GetProductByIdUseCase
import com.rgk.qhatu.feature.product.domain.usecase.GetStorageTypeUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SaveImageProductUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SyncProductUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.GetSaleWithDetailsById
import com.rgk.qhatu.feature.sale.domain.usecase.GetSalesUseCase
import com.rgk.qhatu.feature.sale.domain.usecase.SaveSaleWithDetailsUseCase
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
    factoryOf(::SyncProductUseCase)
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
    factoryOf(::GetAllProductsUseCase)
    factoryOf(::GetStorageTypeUseCase)
    factoryOf(::SaveImageProductUseCase)
    factoryOf(::ObserveCartSummaryUseCase)
    factoryOf(::AddItemToCartUseCase)
    factoryOf(::RemoveItemToCartUseCase)
    factoryOf(::UpdateItemToCartUseCase)
    factoryOf(::GetRefreshCartSummaryUseCase)
    factoryOf(::GetProductByIdUseCase)
    factoryOf(::ObserveCartItemsUseCase)
    factoryOf(::DeleteCartUseCase)
    factoryOf(::ResumeCartUseCase)
    factoryOf(::SaveSaleWithDetailsUseCase)
    factoryOf(::GetSaleWithDetailsById)
    factoryOf(::GetSalesUseCase)
    factoryOf(::GetCartsInactive)
}