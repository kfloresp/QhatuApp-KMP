package com.rgk.qhatu.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.domain.model.Product

@Composable
fun ProductSearchAdvanced(
    products: List<Product>,
    onProductSelected: (Product) -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MinimalToolbar(
            title = "Búsqueda avanzada",
            onClose = { onBack() },
            contentColor = Color.Black,
            backgroundColor = Color.White
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products) { product ->
                ListItem(
                    headlineContent = { Text(product.nombre) },
                    supportingContent = {
                        Text("Código: ${product.id} - EAN: ${product.ean}")
                    },
                    modifier = Modifier
                        .clickable { onProductSelected(product) }
                        .padding(16.dp)
                )
            }
        }
    }
}