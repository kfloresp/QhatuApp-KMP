package com.rgk.qhatu.feature.product.domain.model

import com.rgk.qhatu.common.util.formatAmount
import com.rgk.qhatu.common.util.orZero
import com.rgk.qhatu.feature.cart.domain.model.CartItem
import com.rgk.qhatu.feature.operation.domain.model.OperationDetail

data class Product(
    val id: String = "",
    val ean: String = "",
    val name: String = "",
    val categoryId: String? = null,
    val category: String = "",
    val storageTypeId: String? = null,
    val storageType: String = "",
    val brandId: String? = null,
    val brand: String = "",
    val unitPrice: String = "",
    val unitMeasureId: String = "",
    val unitMeasure: String = "",
    val isBatch: Boolean = false,
    val isActive: Boolean = false,
    val syncedDate: Long = 0,
    val isSynced: Boolean = false,
    val isDeleted: Boolean = false,
    val lastUpdated: Long = 0,
    val imageProduct: List<ImageProduct> = emptyList(),
    val cartItem: CartItem? = null,
) {
    val unitPriceValue: String
        get() = unitPrice.toDouble().formatAmount()
}

enum class StorageType(val value: String) {
    FIFO("First in, first out"),
    FEFO("First expired, first out"),
    LIFO("Last in, first out"),
    LEFO("Last expired, first out"),
}

fun Product.toOperationDetail(): OperationDetail = OperationDetail(
    productId = this.id,
    quantity = this.cartItem?.quantity.orZero(),
    unitPrice = this.cartItem?.unitPrice.orZero(),
    totalDiscount = this.cartItem?.totalDiscount.orZero(),
    batch = this.cartItem?.batch,
    expirationDate = this.cartItem?.expirationDate,
)