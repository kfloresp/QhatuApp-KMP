package com.rgk.qhatu.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.rgk.qhatu.data.database.dao.*
import com.rgk.qhatu.data.database.entity.*

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
    UnitMeasureEntity::class], version = 1, exportSchema = false)
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
}
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDataBase> {
    override fun initialize(): AppDataBase
}
