package com.rgk.qhatu.di

import com.rgk.qhatu.MainViewModel
import com.rgk.qhatu.feature.auth.presentation.auth.AuthViewModel
import com.rgk.qhatu.feature.home.presentation.home.HomeViewModel
import com.rgk.qhatu.feature.sale.presentation.receipt.ReceiptViewModel
import com.rgk.qhatu.feature.sale.presentation.receipt.searchprovider.SearchProviderViewModel
import com.rgk.qhatu.feature.search.presentation.search.SearchViewModel
import com.rgk.qhatu.feature.setting.presentation.brand.BrandViewModel
import com.rgk.qhatu.feature.setting.presentation.category.CategoryViewModel
import com.rgk.qhatu.feature.splash.presentation.splash.SplashViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncAuditLogViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncBrandViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncCategoryViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncClientPaymentViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncClientViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncConfigurationViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncPaymentTransactionViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncProductViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncTransactionDetailViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncTransactionViewModel
import com.rgk.qhatu.feature.setting.presentation.sync.SyncUnitMeasureViewModel
import com.rgk.qhatu.feature.setting.presentation.unitmeasure.UnitMeasureViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::SyncAuditLogViewModel)
    viewModelOf(::SyncBrandViewModel)
    viewModelOf(::SyncCategoryViewModel)
    viewModelOf(::SyncClientPaymentViewModel)
    viewModelOf(::SyncClientViewModel)
    viewModelOf(::SyncConfigurationViewModel)
    viewModelOf(::SyncPaymentTransactionViewModel)
    viewModelOf(::SyncProductViewModel)
    viewModelOf(::SyncTransactionViewModel)
    viewModelOf(::SyncTransactionDetailViewModel)
    viewModelOf(::SyncUnitMeasureViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::SearchProviderViewModel)
    viewModelOf(::ReceiptViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::CategoryViewModel)
    viewModelOf(::BrandViewModel)
    viewModelOf(::UnitMeasureViewModel)
}