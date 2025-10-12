package com.rgk.qhatu.feature.customer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rgk.qhatu.feature.customer.data.database.entity.CustomerFlowEntity

@Dao
interface CustomerFlowDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertCustomerFlow(entity: CustomerFlowEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun updateCustomerFlow(entity: CustomerFlowEntity)

    @Query("SELECT * FROM customer_flow WHERE isDeleted = 0")
    suspend fun getAllCustomerFlow(): List<CustomerFlowEntity>

    @Query("SELECT * FROM customer_flow WHERE isDeleted = 0 and customerFlowId=:customerFlowId")
    suspend fun getCustomerFlowById(customerFlowId: String): CustomerFlowEntity?
}