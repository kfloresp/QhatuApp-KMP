package com.rgk.qhatu.feature.customer.domain.repository

import com.rgk.qhatu.feature.customer.domain.model.Customer

interface CustomerRepository {
    suspend fun insertCustomer(customer: Customer)
    suspend fun updateCustomer(customer: Customer)
    suspend fun deleteCustomer(customer: Customer)
    suspend fun getCustomerById(customerId: String): Customer?
    suspend fun getAllCustomers(): List<Customer>
}