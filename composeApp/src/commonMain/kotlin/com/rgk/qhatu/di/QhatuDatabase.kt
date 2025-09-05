package com.rgk.qhatu.di

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.rgk.qhatu.feature.audit.data.database.dao.AuditLogDao
import com.rgk.qhatu.feature.audit.data.database.entity.AuditLogEntity
import com.rgk.qhatu.feature.cart.data.database.dao.CartDao
import com.rgk.qhatu.feature.cart.data.database.dao.CartItemDao
import com.rgk.qhatu.feature.cart.data.database.entity.CartEntity
import com.rgk.qhatu.feature.cart.data.database.entity.CartItemEntity
import com.rgk.qhatu.feature.customer.data.database.dao.CustomerDao
import com.rgk.qhatu.feature.customer.data.database.entity.CustomerEntity
import com.rgk.qhatu.feature.image_store.data.database.dao.ImageStoreDao
import com.rgk.qhatu.feature.image_store.data.database.entity.ImageStoreEntity
import com.rgk.qhatu.feature.payment.data.database.dao.PaymentDao
import com.rgk.qhatu.feature.payment.data.database.entity.PaymentEntity
import com.rgk.qhatu.feature.product.data.database.dao.ImageProductDao
import com.rgk.qhatu.feature.product.data.database.dao.ProductDao
import com.rgk.qhatu.feature.product.data.database.entity.ImageProductEntity
import com.rgk.qhatu.feature.product.data.database.entity.ProductEntity
import com.rgk.qhatu.feature.setting.data.database.dao.BrandDao
import com.rgk.qhatu.feature.setting.data.database.dao.CategoryDao
import com.rgk.qhatu.feature.setting.data.database.dao.StoreDao
import com.rgk.qhatu.feature.setting.data.database.dao.UnitMeasureDao
import com.rgk.qhatu.feature.setting.data.database.entity.BrandEntity
import com.rgk.qhatu.feature.setting.data.database.entity.CategoryEntity
import com.rgk.qhatu.feature.setting.data.database.entity.StoreEntity
import com.rgk.qhatu.feature.setting.data.database.entity.UnitMeasureEntity
import com.rgk.qhatu.feature.operation.data.database.dao.OperationDao
import com.rgk.qhatu.feature.operation.data.database.dao.OperationDetailDao
import com.rgk.qhatu.feature.purchase.data.database.dao.PurchaseDao
import com.rgk.qhatu.feature.sale.data.database.dao.SaleDao
import com.rgk.qhatu.feature.operation.data.database.entity.OperationDetailEntity
import com.rgk.qhatu.feature.operation.data.database.entity.OperationEntity
import com.rgk.qhatu.feature.purchase.data.database.entity.PurchaseEntity
import com.rgk.qhatu.feature.sale.data.database.entity.SaleEntity

const val DATABASE_NAME = "qhatu_database.db"

@Database(
    entities = [
        AuditLogEntity::class,
        BrandEntity::class,
        CategoryEntity::class,
        CustomerEntity::class,
        PaymentEntity::class,
        ProductEntity::class,
        OperationEntity::class,
        OperationDetailEntity::class,
        SaleEntity::class,
        PurchaseEntity::class,
        UnitMeasureEntity::class,
        StoreEntity::class,
        ImageProductEntity::class,
        CartEntity::class,
        CartItemEntity::class,
        ImageStoreEntity::class,
    ], version = 1, exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun auditLogDao(): AuditLogDao
    abstract fun brandDao(): BrandDao
    abstract fun categoryDao(): CategoryDao
    abstract fun clientDao(): CustomerDao
    abstract fun clientPaymentDao(): PaymentDao
    abstract fun productDao(): ProductDao
    abstract fun unitMeasureDao(): UnitMeasureDao
    abstract fun storeDao(): StoreDao
    abstract fun imageProductDao(): ImageProductDao
    abstract fun cartDao(): CartDao
    abstract fun cartItemDao(): CartItemDao
    abstract fun operationDao(): OperationDao
    abstract fun operationDetailDao(): OperationDetailDao
    abstract fun saleDao(): SaleDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun imageStoreDao(): ImageStoreDao
}

enum class TypeUpsert {
    NEW,
    UPDATE,
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDataBase> {
    override fun initialize(): AppDataBase
}
