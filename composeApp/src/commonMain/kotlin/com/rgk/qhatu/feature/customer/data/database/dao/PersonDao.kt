package com.rgk.qhatu.feature.customer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.rgk.qhatu.feature.customer.data.database.entity.PersonEntity
import com.rgk.qhatu.feature.customer.data.database.entity.PersonWithCustomerRelation

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPerson(personEntity: PersonEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun updatePerson(personEntity: PersonEntity)

    @Transaction
    @Query("SELECT * FROM customer_person WHERE customerId = :customerId")
    suspend fun getPersonWithCustomerById(customerId: String): PersonWithCustomerRelation?

    @Transaction
    @Query("SELECT * FROM customer_person")
    suspend fun getAllPersonsWithCustomer(): List<PersonWithCustomerRelation>
}