package com.rgk.qhatu.feature.product.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.product.data.database.entity.ProductEntity
import com.rgk.qhatu.common.model.SyncStats
import com.rgk.qhatu.feature.product.data.database.entity.ImageProductEntity
import com.rgk.qhatu.feature.product.domain.model.Product

@Dao
interface ImageProductDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: ImageProductEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun update(entity: ImageProductEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun save(entity: List<ImageProductEntity>)

    @Delete
    suspend fun deleteAll(entity: List<ImageProductEntity>)

    @Query(
        """
        SELECT * FROM images_products where productId = :productId
        """
    )
    suspend fun getImagesForProduct(productId:String): List<ImageProductEntity>
}