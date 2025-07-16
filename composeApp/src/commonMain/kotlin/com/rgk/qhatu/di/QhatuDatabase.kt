package com.rgk.qhatu.di

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.rgk.qhatu.feature.audit.data.database.dao.AuditLogDao
import com.rgk.qhatu.feature.audit.data.database.entity.AuditLogEntity
import com.rgk.qhatu.feature.customer.data.database.dao.ClientDao
import com.rgk.qhatu.feature.customer.data.database.entity.ClientEntity
import com.rgk.qhatu.feature.payment.data.database.dao.ClientPaymentDao
import com.rgk.qhatu.feature.payment.data.database.dao.PaymentTransactionDao
import com.rgk.qhatu.feature.payment.data.database.entity.ClientPaymentEntity
import com.rgk.qhatu.feature.payment.data.database.entity.PaymentTransactionEntity
import com.rgk.qhatu.feature.product.data.database.dao.ProductDao
import com.rgk.qhatu.feature.product.data.database.entity.ProductEntity
import com.rgk.qhatu.feature.sale.data.database.dao.TransactionDao
import com.rgk.qhatu.feature.sale.data.database.dao.TransactionDetailDao
import com.rgk.qhatu.feature.sale.data.database.entity.TransactionDetailEntity
import com.rgk.qhatu.feature.sale.data.database.entity.TransactionEntity
import com.rgk.qhatu.feature.setting.data.database.dao.BrandDao
import com.rgk.qhatu.feature.setting.data.database.dao.CategoryDao
import com.rgk.qhatu.feature.setting.data.database.dao.ConfigurationDao
import com.rgk.qhatu.feature.setting.data.database.dao.StoreDao
import com.rgk.qhatu.feature.setting.data.database.dao.UnitMeasureDao
import com.rgk.qhatu.feature.setting.data.database.entity.BrandEntity
import com.rgk.qhatu.feature.setting.data.database.entity.CategoryEntity
import com.rgk.qhatu.feature.setting.data.database.entity.ConfigurationEntity
import com.rgk.qhatu.feature.setting.data.database.entity.StoreEntity
import com.rgk.qhatu.feature.setting.data.database.entity.UnitMeasureEntity

const val DATABASE_NAME = "qhatu_database.db"

@Database(entities = [  ConfigurationEntity::class,
    AuditLogEntity::class,
    BrandEntity::class,
    CategoryEntity::class,
    ClientEntity::class,
    ClientPaymentEntity::class,
    PaymentTransactionEntity::class,
    ProductEntity::class,
    TransactionDetailEntity::class,
    TransactionEntity::class,
    UnitMeasureEntity::class,
    StoreEntity::class,
                     ], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun configurationDao(): ConfigurationDao
    abstract fun auditLogDao(): AuditLogDao
    abstract fun brandDao(): BrandDao
    abstract fun categoryDao(): CategoryDao
    abstract fun clientDao(): ClientDao
    abstract fun clientPaymentDao(): ClientPaymentDao
    abstract fun paymentTransactionDao(): PaymentTransactionDao
    abstract fun productDao(): ProductDao
    abstract fun transactionDetailDao(): TransactionDetailDao
    abstract fun transactionDao(): TransactionDao
    abstract fun unitMeasureDao(): UnitMeasureDao
    abstract fun storeDao(): StoreDao
}
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDataBase> {
    override fun initialize(): AppDataBase
}
