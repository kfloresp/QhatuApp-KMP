package com.rgk.qhatu.di

import com.rgk.qhatu.feature.audit.data.repository.AuditLogRepositoryImpl
import com.rgk.qhatu.feature.auth.data.repository.AuthRepositoryImpl
import com.rgk.qhatu.feature.customer.data.repository.CustomerRepositoryImpl
import com.rgk.qhatu.feature.product.data.repository.ProductRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.BrandRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.CategoryRepositoryImpl
import com.rgk.qhatu.feature.payment.data.repository.ClientPaymentRepositoryImpl
import com.rgk.qhatu.feature.sale.data.repository.PaymentTransactionRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.UnitMeasureRepositoryImpl
import com.rgk.qhatu.feature.sale.data.repository.TransactionDetailRepositoryImpl
import com.rgk.qhatu.feature.sale.data.repository.TransactionRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.ConfigurationRepositoryImpl
import com.rgk.qhatu.feature.audit.domain.repository.AuditLogRepository
import com.rgk.qhatu.feature.auth.domain.repository.AuthRepository
import com.rgk.qhatu.feature.payment.domain.repository.ClientPaymentRepository
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
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<AuditLogRepository> { AuditLogRepositoryImpl(get(), get()) }
    factory<BrandRepository> { BrandRepositoryImpl(get(), get()) }
    factory<CategoryRepository> { CategoryRepositoryImpl(get(), get()) }
    factory<ClientPaymentRepository> { ClientPaymentRepositoryImpl(get(), get()) }
    factory<CustomerRepository> { CustomerRepositoryImpl(get(), get()) }
    factory<ConfigurationRepository> { ConfigurationRepositoryImpl(get(), get()) }
    factory<PaymentTransactionRepository> { PaymentTransactionRepositoryImpl(get(), get()) }
    factory<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    factory<TransactionDetailRepository> { TransactionDetailRepositoryImpl(get(), get()) }
    factory<TransactionRepository> { TransactionRepositoryImpl(get(), get()) }
    factory<UnitMeasureRepository> { UnitMeasureRepositoryImpl(get(), get()) }
    factory<StoreRepository> { StoreRepositoryImpl(get(), get()) }
}