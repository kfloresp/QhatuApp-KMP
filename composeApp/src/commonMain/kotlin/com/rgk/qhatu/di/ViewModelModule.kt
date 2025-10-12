package com.rgk.qhatu.di

import com.rgk.qhatu.MainViewModel
import com.rgk.qhatu.feature.auth.presentation.auth.AuthViewModel
import com.rgk.qhatu.feature.cart.presentation.cart.CartViewModel
import com.rgk.qhatu.feature.cart.presentation.checkout.CheckoutViewModel
import com.rgk.qhatu.feature.customer.presentation.customer.CustomerViewModel
import com.rgk.qhatu.feature.customer.presentation.customerform.CustomerFormViewModel
import com.rgk.qhatu.feature.customer.presentation.customerprofile.CustomerProfileViewModel
import com.rgk.qhatu.feature.customer.presentation.customersummary.CustomerSummaryViewModel
import com.rgk.qhatu.feature.home.presentation.home.HomeViewModel
import com.rgk.qhatu.feature.payment.presentation.payment.PaymentViewModel
import com.rgk.qhatu.feature.payment.presentation.paymentform.PaymentFormViewModel
import com.rgk.qhatu.feature.product.presentation.product.ProductViewModel
import com.rgk.qhatu.feature.product.presentation.productform.ProductFormViewModel
import com.rgk.qhatu.feature.sale.presentation.sale.SaleViewModel
import com.rgk.qhatu.feature.search.presentation.search.SearchViewModel
import com.rgk.qhatu.feature.setting.presentation.brand.BrandViewModel
import com.rgk.qhatu.feature.setting.presentation.category.CategoryViewModel
import com.rgk.qhatu.feature.setting.presentation.store.StoreViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncViewModel
import com.rgk.qhatu.feature.splash.presentation.splash.SplashViewModel
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.UnitMeasureViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::CategoryViewModel)
    viewModelOf(::BrandViewModel)
    viewModelOf(::UnitMeasureViewModel)
    viewModelOf(::StoreViewModel)
    viewModelOf(::SyncViewModel)
    viewModelOf(::CustomerViewModel)
    viewModelOf(::CustomerProfileViewModel)
    viewModelOf(::CustomerSummaryViewModel)
    viewModelOf(::CustomerFormViewModel)
    viewModelOf(::PaymentViewModel)
    viewModelOf(::PaymentFormViewModel)
    viewModelOf(::ProductViewModel)
    viewModelOf(::ProductFormViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::CartViewModel)
    viewModelOf(::CheckoutViewModel)
    viewModelOf(::SaleViewModel)
}