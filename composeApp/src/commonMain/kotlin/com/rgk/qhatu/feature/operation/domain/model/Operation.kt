package com.rgk.qhatu.feature.operation.domain.model

import com.rgk.qhatu.common.util.generateUUID
import com.rgk.qhatu.common.util.getCurrentTimestamp

data class Operation(
    val operationId: String = generateUUID(),
    val type: OperationType,
    val status: OperationStatus = OperationStatus.PENDING,
    val operationDate: Long = getCurrentTimestamp(),
    val lastUpdated: Long = getCurrentTimestamp(),
    val isSynced: Boolean = false,
    val syncedDate: Long = 0L,
    val isDeleted: Boolean = false,
)

enum class OperationType(val value: String) {
    SALE("Sale"),
    PURCHASE("Purchase"),
    INVENTORY("Inventory");
}

enum class OperationStatus(val value: String) {
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");
}