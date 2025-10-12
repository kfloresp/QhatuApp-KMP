package com.rgk.qhatu.feature.product.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.product.data.database.entity.ProductEntity
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.product.data.database.entity.ProductWithDetail
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
            c.name AS category,
            p.storageTypeId,
            "FIFO" AS storageType,
            p.brandId,
            m.name AS brand,
            p.unitPrice,
            p.unitMeasureId,
            um.name AS unitMeasure,
            p.isBatch,
            p.isActive,
            p.syncedDate,
            p.isDeleted,
            p.lastUpdated,
            p.isSynced
        FROM products p
        LEFT JOIN product_category c ON p.categoryId = c.id
        LEFT JOIN product_brand m ON p.brandId = m.id
        LEFT JOIN product_unit_of_measure um ON p.unitMeasureId = um.id
         WHERE p.isDeleted = false
        """
    )
    suspend fun getProductsWithDetails(): List<ProductWithDetail>?

    @Query(
        """
        SELECT 
            p.id,
            p.ean,
            p.name,
            p.categoryId,
            c.name AS category,
            p.storageTypeId,
            "FIFO" || ' (' || "FIRST IN FIRST OUT"|| ')'  AS storageType,
            p.brandId,
            m.name AS brand,
            p.unitPrice,
            p.unitMeasureId,
            um.name AS unitMeasure,
            p.isBatch,
            p.isActive,
            p.syncedDate,
            p.isDeleted,
            p.lastUpdated,
            p.isSynced
        FROM products p
        LEFT JOIN product_category c ON p.categoryId = c.id
        LEFT JOIN product_brand m ON p.brandId = m.id
        LEFT JOIN product_unit_of_measure um ON p.unitMeasureId = um.id
        WHERE p.id = :productId and p.isDeleted = false 
        LIMIT 1
        """
    )
    suspend fun getProductsWithDetailsById(productId: String): ProductWithDetail?

}