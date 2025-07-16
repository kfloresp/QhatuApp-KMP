package com.rgk.qhatu.di

import androidx.room.RoomDatabase
import com.rgk.qhatu.data.AppDataBase
import com.rgk.qhatu.data.feature.audit.database.dao.AuditLogDao
import com.rgk.qhatu.data.feature.customer.database.dao.ClientDao
import com.rgk.qhatu.data.feature.payment.database.dao.ClientPaymentDao
import com.rgk.qhatu.data.feature.payment.database.dao.PaymentTransactionDao
import com.rgk.qhatu.data.feature.product.database.dao.ProductDao
import com.rgk.qhatu.data.feature.sale.database.dao.TransactionDao
import com.rgk.qhatu.data.feature.sale.database.dao.TransactionDetailDao
import com.rgk.qhatu.data.feature.setting.database.dao.BrandDao
import com.rgk.qhatu.data.feature.setting.database.dao.CategoryDao
import com.rgk.qhatu.data.feature.setting.database.dao.ConfigurationDao
import com.rgk.qhatu.data.feature.setting.database.dao.UnitMeasureDao
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
}