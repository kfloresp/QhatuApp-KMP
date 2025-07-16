package com.rgk.qhatu.di

import com.rgk.qhatu.data.audit.remote.AuditLogRemoteDataSource
import com.rgk.qhatu.data.auth.remote.AuthRemoteDataSource
import com.rgk.qhatu.data.customer.remote.ClientRemoteDataSource
import com.rgk.qhatu.data.payment.remote.ClientPaymentRemoteDataSource
import com.rgk.qhatu.data.payment.remote.PaymentTransactionRemoteDataSource
import com.rgk.qhatu.data.product.remote.ProductRemoteDataSource
import com.rgk.qhatu.data.sale.remote.TransactionDetailRemoteDataSource
import com.rgk.qhatu.data.sale.remote.TransactionRemoteDataSource
import com.rgk.qhatu.data.setting.remote.BrandRemoteDataSource
import com.rgk.qhatu.data.setting.remote.CategoryRemoteDataSource
import com.rgk.qhatu.data.setting.remote.ConfigurationRemoteDataSource
import com.rgk.qhatu.data.setting.remote.UnitMeasureRemoteDataSource
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