package com.rgk.qhatu.data.feature.product.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.data.feature.product.database.entity.ProductEntity
import com.rgk.qhatu.domain.common.SyncStats

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: ProductEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(entity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: List<ProductEntity>)

    @Query("SELECT * FROM products")
    suspend fun fetchAll(): List<ProductEntity>

    @Query("SELECT COUNT(*) as count, MAX(fecha_sincronizado) as lastUpdated FROM products")
    suspend fun getStats(): SyncStats

    @Query("SELECT id FROM products WHERE flag_sincronizado = 1")
    suspend fun getSyncedIds(): List<String>

    @Query("DELETE FROM products WHERE flag_sincronizado != 1")
    suspend fun deleteUnsynced()

    @Query("SELECT * FROM products WHERE ean LIKE '%' || :query || '%' COLLATE NOCASE")
    suspend fun queryByEan(query: String): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id LIKE '%' || :query || '%' COLLATE NOCASE")
    suspend fun queryByCode(query: String): List<ProductEntity>

    @Query("SELECT * FROM products WHERE nombre LIKE '%' || :query || '%' COLLATE NOCASE")
    suspend fun queryByName(query: String): List<ProductEntity>

}