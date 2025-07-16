package com.rgk.qhatu.di

import com.rgk.qhatu.data.feature.audit.repository.AuditLogRepositoryImpl
import com.rgk.qhatu.data.feature.auth.repository.AuthRepositoryImpl
import com.rgk.qhatu.data.feature.customer.repository.ClientRepositoryImpl
import com.rgk.qhatu.data.feature.product.repository.ProductRepositoryImpl
import com.rgk.qhatu.data.feature.setting.repository.BrandRepositoryImpl
import com.rgk.qhatu.data.feature.setting.repository.CategoryRepositoryImpl
import com.rgk.qhatu.data.feature.payment.repository.ClientPaymentRepositoryImpl
import com.rgk.qhatu.data.feature.payment.repository.PaymentTransactionRepositoryImpl
import com.rgk.qhatu.data.feature.setting.repository.UnitMeasureRepositoryImpl
import com.rgk.qhatu.data.feature.sale.repository.TransactionDetailRepositoryImpl
import com.rgk.qhatu.data.feature.sale.repository.TransactionRepositoryImpl
import com.rgk.qhatu.data.feature.setting.repository.ConfigurationRepositoryImpl
import com.rgk.qhatu.domain.feature.audit.repository.AuditLogRepository
import com.rgk.qhatu.domain.feature.auth.repository.AuthRepository
import com.rgk.qhatu.domain.feature.setting.repository.BrandRepository
import com.rgk.qhatu.domain.feature.setting.repository.CategoryRepository
import com.rgk.qhatu.domain.feature.payment.repository.ClientPaymentRepository
import com.rgk.qhatu.domain.feature.customer.repository.ClientRepository
import com.rgk.qhatu.domain.feature.setting.repository.ConfigurationRepository
import com.rgk.qhatu.domain.feature.payment.repository.PaymentTransactionRepository
import com.rgk.qhatu.domain.feature.product.repository.ProductRepository
import com.rgk.qhatu.domain.feature.sale.repository.TransactionDetailRepository
import com.rgk.qhatu.domain.feature.sale.repository.TransactionRepository
import com.rgk.qhatu.domain.feature.setting.repository.UnitMeasureRepository
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