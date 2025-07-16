package com.rgk.qhatu.di

import com.rgk.qhatu.data.feature.audit.remote.AuditLogRemoteDataSource
import com.rgk.qhatu.data.feature.auth.remote.AuthRemoteDataSource
import com.rgk.qhatu.data.feature.customer.remote.ClientRemoteDataSource
import com.rgk.qhatu.data.feature.payment.remote.ClientPaymentRemoteDataSource
import com.rgk.qhatu.data.feature.payment.remote.PaymentTransactionRemoteDataSource
import com.rgk.qhatu.data.feature.product.remote.ProductRemoteDataSource
import com.rgk.qhatu.data.feature.sale.remote.TransactionDetailRemoteDataSource
import com.rgk.qhatu.data.feature.sale.remote.TransactionRemoteDataSource
import com.rgk.qhatu.data.feature.setting.remote.BrandRemoteDataSource
import com.rgk.qhatu.data.feature.setting.remote.CategoryRemoteDataSource
import com.rgk.qhatu.data.feature.setting.remote.ConfigurationRemoteDataSource
import com.rgk.qhatu.data.feature.setting.remote.UnitMeasureRemoteDataSource
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
    factoryOf(::PaymentTransactionRemoteDataSource)
    factoryOf(::ProductRemoteDataSource)
    factoryOf(::TransactionDetailRemoteDataSource)
    factoryOf(::TransactionRemoteDataSource)
    factoryOf(::UnitMeasureRemoteDataSource)
}