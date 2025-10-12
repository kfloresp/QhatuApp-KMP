package com.rgk.qhatu.feature.customer.data.repository

import com.rgk.qhatu.feature.customer.data.database.dao.CustomerFlowDao
import com.rgk.qhatu.feature.customer.domain.mapper.toDomain
import com.rgk.qhatu.feature.customer.domain.mapper.toEntity
import com.rgk.qhatu.feature.customer.domain.model.CustomerFlow
import com.rgk.qhatu.feature.customer.domain.repository.CustomerFlowRepository

class CustomerFlowRepositoryImpl(
    private val sourceLocal: CustomerFlowDao,
) : CustomerFlowRepository {
    override suspend fun insertCustomerFlow(customer: CustomerFlow) {
        sourceLocal.insertCustomerFlow(customer.toEntity())
    }

    override suspend fun updateCustomerFlow(customer: CustomerFlow) {
        sourceLocal.updateCustomerFlow(customer.toEntity())
    }

    override suspend fun deleteCustomerFlow(customer: CustomerFlow) {
        sourceLocal.updateCustomerFlow(customer.toEntity().copy(isDeleted = true))
    }

    override suspend fun getCustomerFlowById(customerId: String): CustomerFlow? {
        return sourceLocal.getCustomerFlowById(customerId)?.toDomain()
    }

    override suspend fun getAllCustomerFlows(): List<CustomerFlow> {
        return sourceLocal.getAllCustomerFlow().map { it.toDomain() }
    }
}