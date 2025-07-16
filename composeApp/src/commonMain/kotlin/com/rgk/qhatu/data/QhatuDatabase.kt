package com.rgk.qhatu.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.rgk.qhatu.data.audit.database.dao.AuditLogDao
import com.rgk.qhatu.data.audit.database.entity.AuditLogEntity
import com.rgk.qhatu.data.customer.database.dao.ClientDao
import com.rgk.qhatu.data.customer.database.entity.ClientEntity
import com.rgk.qhatu.data.payment.database.dao.ClientPaymentDao
import com.rgk.qhatu.data.payment.database.dao.PaymentTransactionDao
import com.rgk.qhatu.data.payment.database.entity.ClientPaymentEntity
import com.rgk.qhatu.data.payment.database.entity.PaymentTransactionEntity
import com.rgk.qhatu.data.product.database.dao.ProductDao
import com.rgk.qhatu.data.product.database.entity.ProductEntity
import com.rgk.qhatu.data.sale.database.dao.TransactionDao
import com.rgk.qhatu.data.sale.database.dao.TransactionDetailDao
import com.rgk.qhatu.data.sale.database.entity.TransactionDetailEntity
import com.rgk.qhatu.data.sale.database.entity.TransactionEntity
import com.rgk.qhatu.data.setting.database.dao.BrandDao
import com.rgk.qhatu.data.setting.database.dao.CategoryDao
import com.rgk.qhatu.data.setting.database.dao.ConfigurationDao
import com.rgk.qhatu.data.setting.database.dao.StoreDao
import com.rgk.qhatu.data.setting.database.dao.UnitMeasureDao
import com.rgk.qhatu.data.setting.database.entity.BrandEntity
import com.rgk.qhatu.data.setting.database.entity.CategoryEntity
import com.rgk.qhatu.data.setting.database.entity.ConfigurationEntity
import com.rgk.qhatu.data.setting.database.entity.StoreEntity
import com.rgk.qhatu.data.setting.database.entity.UnitMeasureEntity

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
