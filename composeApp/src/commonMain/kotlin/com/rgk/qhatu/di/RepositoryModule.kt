package com.rgk.qhatu.di

import com.rgk.qhatu.data.audit.repository.AuditLogRepositoryImpl
import com.rgk.qhatu.data.auth.repository.AuthRepositoryImpl
import com.rgk.qhatu.data.customer.repository.ClientRepositoryImpl
import com.rgk.qhatu.data.product.repository.ProductRepositoryImpl
import com.rgk.qhatu.data.repository.BrandRepositoryImpl
import com.rgk.qhatu.data.repository.CategoryRepositoryImpl
import com.rgk.qhatu.data.repository.ClientPaymentRepositoryImpl
import com.rgk.qhatu.data.repository.PaymentTransactionRepositoryImpl
import com.rgk.qhatu.data.repository.UnitMeasureRepositoryImpl
import com.rgk.qhatu.data.sale.repository.TransactionDetailRepositoryImpl
import com.rgk.qhatu.data.sale.repository.TransactionRepositoryImpl
import com.rgk.qhatu.data.setting.repository.ConfigurationRepositoryImpl
import com.rgk.qhatu.domain.repository.AuditLogRepository
import com.rgk.qhatu.domain.repository.AuthRepository
import com.rgk.qhatu.domain.repository.BrandRepository
import com.rgk.qhatu.domain.repository.CategoryRepository
import com.rgk.qhatu.domain.repository.ClientPaymentRepository
import com.rgk.qhatu.domain.repository.ClientRepository
import com.rgk.qhatu.domain.repository.ConfigurationRepository
import com.rgk.qhatu.domain.repository.PaymentTransactionRepository
import com.rgk.qhatu.domain.repository.ProductRepository
import com.rgk.qhatu.domain.repository.TransactionDetailRepository
import com.rgk.qhatu.domain.repository.TransactionRepository
import com.rgk.qhatu.domain.repository.UnitMeasureRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<AuditLogRepository> { AuditLogRepositoryImpl(get(), get()) }
    factory<BrandRepository> { BrandRepositoryImpl(get(), get()) }
    factory<CategoryRepository> { CategoryRepositoryImpl(get(), get()) }
    factory<ClientPaymentRepository> { ClientPaymentRepositoryImpl(get(), get()) }
    factory<ClientRepository> { ClientRepositoryImpl(get(), get()) }
    factory<ConfigurationRepository> { ConfigurationRepositoryImpl(get(), get()) }
    factory<PaymentTransactionRepository> { PaymentTransactionRepositoryImpl(get(), get()) }
    factory<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    factory<TransactionDetailRepository> { TransactionDetailRepositoryImpl(get(), get()) }
    factory<TransactionRepository> { TransactionRepositoryImpl(get(), get()) }
    factory<UnitMeasureRepository> { UnitMeasureRepositoryImpl(get(), get()) }
}