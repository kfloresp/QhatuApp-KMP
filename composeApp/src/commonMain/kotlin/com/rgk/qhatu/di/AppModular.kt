package com.rgk.qhatu.di

import androidx.room.RoomDatabase
import com.rgk.qhatu.data.audit.database.dao.AuditLogDao
import com.rgk.qhatu.data.audit.remote.AuditLogRemoteDataSource
import com.rgk.qhatu.data.audit.repository.AuditLogRepositoryImpl
import com.rgk.qhatu.data.auth.remote.AuthRemoteDataSource
import com.rgk.qhatu.data.customer.database.dao.ClientDao
import com.rgk.qhatu.data.customer.remote.ClientRemoteDataSource
import com.rgk.qhatu.data.customer.repository.ClientRepositoryImpl
import com.rgk.qhatu.data.AppDataBase
import com.rgk.qhatu.data.auth.repository.AuthRepositoryImpl
import com.rgk.qhatu.data.payment.database.dao.ClientPaymentDao
import com.rgk.qhatu.data.payment.database.dao.PaymentTransactionDao
import com.rgk.qhatu.data.payment.remote.ClientPaymentRemoteDataSource
import com.rgk.qhatu.data.payment.remote.PaymentTransactionRemoteDataSource
import com.rgk.qhatu.data.product.database.dao.ProductDao
import com.rgk.qhatu.data.product.remote.ProductRemoteDataSource
import com.rgk.qhatu.data.product.repository.ProductRepositoryImpl
import com.rgk.qhatu.data.repository.*
import com.rgk.qhatu.data.sale.database.dao.TransactionDao
import com.rgk.qhatu.data.sale.database.dao.TransactionDetailDao
import com.rgk.qhatu.data.sale.remote.TransactionDetailRemoteDataSource
import com.rgk.qhatu.data.sale.remote.TransactionRemoteDataSource
import com.rgk.qhatu.data.sale.repository.TransactionDetailRepositoryImpl
import com.rgk.qhatu.data.sale.repository.TransactionRepositoryImpl
import com.rgk.qhatu.data.setting.database.dao.BrandDao
import com.rgk.qhatu.data.setting.database.dao.CategoryDao
import com.rgk.qhatu.data.setting.database.dao.ConfigurationDao
import com.rgk.qhatu.data.setting.database.dao.UnitMeasureDao
import com.rgk.qhatu.data.setting.remote.BrandRemoteDataSource
import com.rgk.qhatu.data.setting.remote.CategoryRemoteDataSource
import com.rgk.qhatu.data.setting.remote.ConfigurationRemoteDataSource
import com.rgk.qhatu.data.setting.remote.UnitMeasureRemoteDataSource
import com.rgk.qhatu.data.setting.repository.ConfigurationRepositoryImpl
import com.rgk.qhatu.domain.repository.*
import com.rgk.qhatu.domain.usecase.AuthUseCase
import com.rgk.qhatu.domain.usecase.unitMeasure.*
import com.rgk.qhatu.domain.usecase.transaction.*
import com.rgk.qhatu.domain.usecase.auditLog.*
import com.rgk.qhatu.domain.usecase.brand.*
import com.rgk.qhatu.domain.usecase.client.*
import com.rgk.qhatu.domain.usecase.clientPayment.*
import com.rgk.qhatu.domain.usecase.category.*
import com.rgk.qhatu.domain.usecase.configuration.GetConfigurationStatsUseCase
import com.rgk.qhatu.domain.usecase.configuration.SyncConfigurationUseCase
import com.rgk.qhatu.domain.usecase.paymentTransaction.*
import com.rgk.qhatu.domain.usecase.product.*
import com.rgk.qhatu.domain.usecase.transactionDetail.*
import com.rgk.qhatu.ui.feature.home.HomeViewModel
import com.rgk.qhatu.ui.feature.auth.AuthViewModel
import com.rgk.qhatu.ui.feature.receipt.ReceiptViewModel
import com.rgk.qhatu.ui.feature.receipt.searchprovider.SearchProviderViewModel
import com.rgk.qhatu.ui.feature.search.SearchViewModel
import com.rgk.qhatu.ui.feature.splash.SplashViewModel
import com.rgk.qhatu.ui.feature.sync.*
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.*
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }
    single {
        get<RoomDatabase.Builder<AppDataBase>>().build()
    }
    single<AuditLogDao> { get<AppDataBase>().auditLogDao() }
    single<BrandDao> { get<AppDataBase>().brandDao() }
    single<CategoryDao> { get<AppDataBase>().categoryDao() }
    single<ClientDao> { get<AppDataBase>().clientDao() }
    single<ClientPaymentDao> { get<AppDataBase>().clientPaymentDao() }
    single<PaymentTransactionDao> { get<AppDataBase>().paymentTransactionDao() }
    single<ConfigurationDao> { get<AppDataBase>().configurationDao() }
    single<ProductDao> { get<AppDataBase>().productDao() }
    single<TransactionDao> { get<AppDataBase>().transactionDao() }
    single<TransactionDetailDao> { get<AppDataBase>().transactionDetailDao() }
    single<UnitMeasureDao> { get<AppDataBase>().unitMeasureDao() }
}
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
val domainModule = module {
    factory<AuthUseCase> { AuthUseCase(get()) }

    factoryOf(::GetAuditLogStatsUseCase)
    factoryOf(::GetBrandStatsUseCase)
    factoryOf(::GetCategoryStatsUseCase)
    factoryOf(::GetClientStatsUseCase)
    factoryOf(::GetClientPaymentStatsUseCase)
    factoryOf(::GetConfigurationStatsUseCase)
    factoryOf(::GetPaymentTransactionStatsUseCase)
    factoryOf(::GetProductStatsUseCase)
    factoryOf(::GetTransactionDetailStatsUseCase)
    factoryOf(::GetTransactionStatsUseCase)
    factoryOf(::GetUnitMeasureStatsUseCase)

    factoryOf(::SyncAuditLogUseCase)
    factoryOf(::SyncBrandUseCase)
    factoryOf(::SyncCategoryUseCase)
    factoryOf(::SyncClientUseCase)
    factoryOf(::SyncClientPaymentUseCase)
    factoryOf(::SyncConfigurationUseCase)
    factoryOf(::SyncPaymentTransactionUseCase)
    factoryOf(::SyncProductUseCase)
    factoryOf(::SyncTransactionUseCase)
    factoryOf(::SyncTransactionDetailUseCase)
    factoryOf(::SyncUnitMeasureUseCase)
    factoryOf(::GetProductFromQueryUseCase)
    factoryOf(::GetClientUseCase)
    factoryOf(::GetProviderUseCase)
}

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

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            dataSourceModule,
            repositoryModule,
            domainModule,
            viewModelModule,
            nativeModule
        )
    }
}

