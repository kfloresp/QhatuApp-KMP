package com.rgk.qhatu.di

import com.rgk.qhatu.feature.audit.data.repository.AuditLogRepositoryImpl
import com.rgk.qhatu.feature.auth.data.repository.AuthRepositoryImpl
import com.rgk.qhatu.feature.customer.data.repository.CustomerRepositoryImpl
import com.rgk.qhatu.feature.product.data.repository.ProductRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.BrandRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.CategoryRepositoryImpl
import com.rgk.qhatu.feature.payment.data.repository.PaymentRepositoryImpl
import com.rgk.qhatu.feature.sale.data.repository.PaymentTransactionRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.UnitMeasureRepositoryImpl
import com.rgk.qhatu.feature.sale.data.repository.TransactionDetailRepositoryImpl
import com.rgk.qhatu.feature.sale.data.repository.TransactionRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.ConfigurationRepositoryImpl
import com.rgk.qhatu.feature.audit.domain.repository.AuditLogRepository
import com.rgk.qhatu.feature.auth.domain.repository.AuthRepository
import com.rgk.qhatu.feature.cart.data.repository.CartRepositoryImpl
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
import com.rgk.qhatu.feature.payment.domain.repository.PaymentRepository
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository
import com.rgk.qhatu.feature.sale.domain.repository.PaymentTransactionRepository
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository
import com.rgk.qhatu.feature.sale.domain.repository.TransactionDetailRepository
import com.rgk.qhatu.feature.sale.domain.repository.TransactionRepository
import com.rgk.qhatu.feature.setting.data.repository.StoreRepositoryImpl
import com.rgk.qhatu.feature.setting.domain.repository.BrandRepository
import com.rgk.qhatu.feature.setting.domain.repository.CategoryRepository
import com.rgk.qhatu.feature.setting.domain.repository.ConfigurationRepository
import com.rgk.qhatu.feature.setting.domain.repository.StoreRepository
import com.rgk.qhatu.feature.setting.domain.repository.UnitMeasureRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factoryOf(::AuthRepositoryImpl) bind AuthRepository::class
    factoryOf(::AuditLogRepositoryImpl) bind AuditLogRepository::class
    factoryOf(::BrandRepositoryImpl) bind BrandRepository::class
    factoryOf(::CategoryRepositoryImpl) bind CategoryRepository::class
    factoryOf(::PaymentRepositoryImpl) bind PaymentRepository::class
    factoryOf(::CustomerRepositoryImpl) bind CustomerRepository::class
    factoryOf(::ConfigurationRepositoryImpl) bind ConfigurationRepository::class
    factoryOf(::PaymentTransactionRepositoryImpl) bind PaymentTransactionRepository::class
    factoryOf(::ProductRepositoryImpl) bind ProductRepository::class
    factoryOf(::TransactionDetailRepositoryImpl) bind TransactionDetailRepository::class
    factoryOf(::TransactionRepositoryImpl) bind TransactionRepository::class
    factoryOf(::UnitMeasureRepositoryImpl) bind UnitMeasureRepository::class
    factoryOf(::StoreRepositoryImpl) bind StoreRepository::class
    singleOf(::CartRepositoryImpl) bind CartRepository::class
}
