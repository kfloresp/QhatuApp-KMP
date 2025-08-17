package com.rgk.qhatu.feature.product.presentation.productform

import com.rgk.qhatu.feature.product.domain.model.Product

data class ProductFormValidationState(
    val fields: Product = Product(),
    val isValid: Boolean = false,
)