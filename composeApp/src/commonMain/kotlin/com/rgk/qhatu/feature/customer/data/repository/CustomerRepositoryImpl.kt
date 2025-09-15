package com.rgk.qhatu.feature.customer.data.repository

import com.rgk.qhatu.feature.customer.data.database.dao.CustomerDao
import com.rgk.qhatu.feature.customer.domain.mapper.toDomain
import com.rgk.qhatu.feature.customer.domain.mapper.toEntity
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.customer.domain.repository.CustomerRepository

class CustomerRepositoryImpl(
    private val sourceLocal: CustomerDao,
) : CustomerRepository {
    override suspend fun insertCustomer(customer: Customer) {
        sourceLocal.insertCustomer(customer.toEntity())
    }

    override suspend fun updateCustomer(customer: Customer) {
        sourceLocal.updateCustomer(customer.toEntity())
    }

    override suspend fun deleteCustomer(customer: Customer) {
        sourceLocal.updateCustomer(customer.toEntity().copy(isDeleted = true))
    }

    override suspend fun getCustomerById(customerId: String): Customer? {
        return sourceLocal.getCustomerById(customerId)?.toDomain()
    }

    override suspend fun getAllCustomers(): List<Customer> {
        return sourceLocal.getAllCustomer().map { it.toDomain() }
    }

}