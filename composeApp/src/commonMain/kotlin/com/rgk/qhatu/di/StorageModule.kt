package com.rgk.qhatu.di

import androidx.room.RoomDatabase
import com.rgk.qhatu.feature.audit.data.database.dao.AuditLogDao
import com.rgk.qhatu.feature.customer.data.database.dao.ClientDao
import com.rgk.qhatu.feature.payment.data.database.dao.ClientPaymentDao
import com.rgk.qhatu.feature.payment.data.database.dao.PaymentTransactionDao
import com.rgk.qhatu.feature.product.data.database.dao.ProductDao
import com.rgk.qhatu.feature.sale.data.database.dao.TransactionDao
import com.rgk.qhatu.feature.sale.data.database.dao.TransactionDetailDao
import com.rgk.qhatu.feature.setting.data.database.dao.BrandDao
import com.rgk.qhatu.feature.setting.data.database.dao.CategoryDao
import com.rgk.qhatu.feature.setting.data.database.dao.ConfigurationDao
import com.rgk.qhatu.feature.setting.data.database.dao.StoreDao
import com.rgk.qhatu.feature.setting.data.database.dao.UnitMeasureDao
import org.koin.dsl.module

val databaseModule = module {
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
    single<StoreDao> { get<AppDataBase>().storeDao() }
}