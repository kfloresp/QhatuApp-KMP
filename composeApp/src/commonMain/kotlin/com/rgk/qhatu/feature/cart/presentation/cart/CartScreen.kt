package com.rgk.qhatu.feature.cart.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.empty.EmptySection
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.feature.cart.presentation.cart.component.ItemProductCart
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.presentation.product.component.ProductActions
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_cart_empty

@Composable
fun CartScreen(
    uiState: CartUiState,
    productActions: ProductActions,
    onOptionsProduct: (Product) -> Unit,
    setLoading: (Boolean) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        setLoading(uiState is CartUiState.Loading)
        if (uiState is CartUiState.Empty) {
            EmptySection(Res.string.tx_cart_empty)
        }
        if (uiState is CartUiState.Error) {
            ErrorSection(uiState.message)
        }
        if (uiState is CartUiState.Success) {
            val items = uiState.result
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { product ->
                    ItemProductCart(product = product, productActions = productActions, onOptionsProduct = onOptionsProduct)
                }
            }
        }
    }
}