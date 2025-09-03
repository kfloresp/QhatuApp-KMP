package com.rgk.qhatu.di

import com.rgk.qhatu.feature.audit.data.repository.AuditLogRepositoryImpl
import com.rgk.qhatu.feature.auth.data.repository.AuthRepositoryImpl
import com.rgk.qhatu.feature.customer.data.repository.CustomerRepositoryImpl
import com.rgk.qhatu.feature.product.data.repository.ProductRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.BrandRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.CategoryRepositoryImpl
import com.rgk.qhatu.feature.payment.data.repository.PaymentRepositoryImpl
import com.rgk.qhatu.feature.setting.data.repository.UnitMeasureRepositoryImpl
import com.rgk.qhatu.feature.audit.domain.repository.AuditLogRepository
import com.rgk.qhatu.feature.auth.domain.repository.AuthRepository
import com.rgk.qhatu.feature.cart.data.repository.CartRepositoryImpl
import com.rgk.qhatu.feature.cart.domain.repository.CartRepository
import com.rgk.qhatu.feature.payment.domain.repository.PaymentRepository
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository
import com.rgk.qhatu.feature.operation.data.repository.OperationDetailRepositoryImpl
import com.rgk.qhatu.feature.operation.data.repository.OperationRepositoryImpl
import com.rgk.qhatu.feature.operation.domain.repository.OperationDetailRepository
import com.rgk.qhatu.feature.operation.domain.repository.OperationRepository
import com.rgk.qhatu.feature.product.domain.repository.ProductRepository
import com.rgk.qhatu.feature.purchase.data.repository.PurchaseRepositoryImpl
import com.rgk.qhatu.feature.purchase.domain.repository.PurchaseRepository
import com.rgk.qhatu.feature.sale.data.repository.SaleRepositoryImpl
import com.rgk.qhatu.feature.sale.domain.repository.SaleRepository
import com.rgk.qhatu.feature.setting.data.repository.StoreRepositoryImpl
import com.rgk.qhatu.feature.setting.domain.repository.BrandRepository
import com.rgk.qhatu.feature.setting.domain.repository.CategoryRepository
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
    factoryOf(::ProductRepositoryImpl) bind ProductRepository::class
    factoryOf(::UnitMeasureRepositoryImpl) bind UnitMeasureRepository::class
    factoryOf(::StoreRepositoryImpl) bind StoreRepository::class
    singleOf(::CartRepositoryImpl) bind CartRepository::class
    factoryOf(::OperationDetailRepositoryImpl) bind OperationDetailRepository::class
    factoryOf(::OperationRepositoryImpl) bind OperationRepository::class
    factoryOf(::SaleRepositoryImpl) bind SaleRepository::class
    factoryOf(::PurchaseRepositoryImpl) bind PurchaseRepository::class
}
