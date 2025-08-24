package com.rgk.qhatu.di

import com.rgk.qhatu.feature.audit.data.remote.AuditLogRemoteDataSource
import com.rgk.qhatu.feature.auth.data.remote.AuthRemoteDataSource
import com.rgk.qhatu.feature.customer.data.remote.ClientRemoteDataSource
import com.rgk.qhatu.feature.payment.data.remote.ClientPaymentRemoteDataSource
import com.rgk.qhatu.feature.product.data.remote.ProductRemoteDataSource
import com.rgk.qhatu.feature.sale.data.remote.TransactionDetailRemoteDataSource
import com.rgk.qhatu.feature.sale.data.remote.TransactionRemoteDataSource
import com.rgk.qhatu.feature.setting.data.remote.BrandRemoteDataSource
import com.rgk.qhatu.feature.setting.data.remote.CategoryRemoteDataSource
import com.rgk.qhatu.feature.setting.data.remote.ConfigurationRemoteDataSource
import com.rgk.qhatu.feature.setting.data.remote.StoreRemoteDataSource
import com.rgk.qhatu.feature.setting.data.remote.UnitMeasureRemoteDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataSourceModule = module {
    factoryOf(::AuthRemoteDataSource)
    factoryOf(::AuditLogRemoteDataSource)
    factoryOf(::BrandRemoteDataSource)
    factoryOf(::CategoryRemoteDataSource)
    factoryOf(::ClientPaymentRemoteDataSource)
    factoryOf(::ClientRemoteDataSource)
    factoryOf(::ConfigurationRemoteDataSource)
    factoryOf(::ProductRemoteDataSource)
    factoryOf(::TransactionDetailRemoteDataSource)
    factoryOf(::TransactionRemoteDataSource)
    factoryOf(::UnitMeasureRemoteDataSource)
    factoryOf(::StoreRemoteDataSource)
}