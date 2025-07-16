package com.rgk.qhatu.di

import com.rgk.qhatu.ui.feature.auth.AuthViewModel
import com.rgk.qhatu.ui.feature.home.HomeViewModel
import com.rgk.qhatu.ui.feature.receipt.ReceiptViewModel
import com.rgk.qhatu.ui.feature.receipt.searchprovider.SearchProviderViewModel
import com.rgk.qhatu.ui.feature.search.SearchViewModel
import com.rgk.qhatu.ui.feature.splash.SplashViewModel
import com.rgk.qhatu.ui.feature.sync.SyncAuditLogViewModel
import com.rgk.qhatu.ui.feature.sync.SyncBrandViewModel
import com.rgk.qhatu.ui.feature.sync.SyncCategoryViewModel
import com.rgk.qhatu.ui.feature.sync.SyncClientPaymentViewModel
import com.rgk.qhatu.ui.feature.sync.SyncClientViewModel
import com.rgk.qhatu.ui.feature.sync.SyncConfigurationViewModel
import com.rgk.qhatu.ui.feature.sync.SyncPaymentTransactionViewModel
import com.rgk.qhatu.ui.feature.sync.SyncProductViewModel
import com.rgk.qhatu.ui.feature.sync.SyncTransactionDetailViewModel
import com.rgk.qhatu.ui.feature.sync.SyncTransactionViewModel
import com.rgk.qhatu.ui.feature.sync.SyncUnitMeasureViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
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
}