package com.rgk.qhatu.feature.setting.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.setting.data.database.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: List<CategoryEntity>)

    @Update
    suspend fun update(entity: CategoryEntity)

    @Query("SELECT * FROM product_category where isDeleted = false")
    suspend fun fetchAll(): List<CategoryEntity>
}
