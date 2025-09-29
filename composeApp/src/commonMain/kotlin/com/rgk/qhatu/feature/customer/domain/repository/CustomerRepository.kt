package com.rgk.qhatu.feature.customer.domain.repository

import com.rgk.qhatu.di.TypeUpsert
import com.rgk.qhatu.feature.customer.domain.model.Customer

interface CustomerRepository {
    suspend fun upsertCustomer(customer: Customer, type: TypeUpsert)
    suspend fun deleteCustomer(customer: Customer)
    suspend fun getCustomerById(customerId: String): Customer?
    suspend fun getAllCustomers(): List<Customer>
}