package com.rgk.qhatu.feature.product.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.product.data.database.entity.ProductEntity
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.product.domain.model.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: ProductEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(entity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: List<ProductEntity>)

    @Query("SELECT COUNT(*) as count, MAX(syncedDate) as lastUpdated FROM products")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM products WHERE isSynced = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM products WHERE isSynced != 1")
    suspend fun deleteUnsynced()

    @Query(
        """
        SELECT 
            p.id,
            p.ean,
            p.name,
            p.categoryId,
            c.nombre AS category,
            p.storageTypeId,
            ta.nombre AS storageType,
            p.brandId,
            m.nombre AS brand,
            p.unitPrice,
            p.unitMeasureId,
            um.nombre AS unitMeasure,
            p.isBatch,
            p.isActive,
            p.syncedDate,
            p.isDeleted,
            p.lastUpdated,
            p.isSynced
        FROM products p
        LEFT JOIN categories c ON p.categoryId = c.id
        LEFT JOIN configurations ta ON p.storageTypeId = ta.id
        LEFT JOIN brands m ON p.brandId = m.id
        LEFT JOIN unit_measures um ON p.unitMeasureId = um.id
        """
    )
    suspend fun getProductsWithDetails(): List<Product>

    @Query(
        """
        SELECT 
            p.id,
            p.ean,
            p.name,
            p.categoryId,
            c.nombre AS category,
            p.storageTypeId,
            ta.nombre AS storageType,
            p.brandId,
            m.nombre AS brand,
            p.unitPrice,
            p.unitMeasureId,
            um.nombre AS unitMeasure,
            p.isBatch,
            p.isActive,
            p.syncedDate,
            p.isDeleted,
            p.lastUpdated,
            p.isSynced
        FROM products p
        LEFT JOIN categories c ON p.categoryId = c.id
        LEFT JOIN configurations ta ON p.storageTypeId = ta.id
        LEFT JOIN brands m ON p.brandId = m.id
        LEFT JOIN unit_measures um ON p.unitMeasureId = um.id
        WHERE p.id = :productId
        """
    )
    suspend fun getProductsWithDetailsById(productId: String): List<Product>

}