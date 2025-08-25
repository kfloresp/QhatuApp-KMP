package com.rgk.qhatu.feature.operation.domain.model

data class Operation(
    val operationId: String,
    val type: OperationType,
    val status: OperationStatus,
    val operationDate: Long,
    val lastUpdated: Long = 0L,
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