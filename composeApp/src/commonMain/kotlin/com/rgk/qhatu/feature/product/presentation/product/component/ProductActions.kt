package com.rgk.qhatu.feature.product.presentation.product.component

import com.rgk.qhatu.feature.product.domain.model.Product

interface ProductActions {
    fun onAddProduct(product: Product, count: Int)
    fun onUpdateQuantityProduct(product: Product, count: Int)
    fun onRemoveProduct(product: Product)

}
