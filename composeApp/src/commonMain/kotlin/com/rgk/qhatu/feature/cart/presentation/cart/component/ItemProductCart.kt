package com.rgk.qhatu.feature.cart.presentation.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rgk.qhatu.common.theme.QhatuTheme
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.presentation.product.component.ProductActionSection
import com.rgk.qhatu.feature.product.presentation.product.component.ProductActions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.image_place_holder

@Composable
fun ItemProductCart(
    product: Product,
    productActions: ProductActions? = null,
    onClick: (Product) -> Unit = {},
) {
    val cartItem = product.cartItem
    val count = cartItem?.quantity ?: 0

    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { onClick(product) }
            .padding(8.dp).border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(12.dp)
            ).padding(8.dp)
    ) {
        AsyncImage(
            model = product.imageProduct.firstOrNull()?.filename ?: "",
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            placeholder = painterResource(resource = Res.drawable.image_place_holder),
            error = painterResource(resource = Res.drawable.image_place_holder)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column() {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.unitMeasure,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.unitPriceValue, style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            productActions?.let {
                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd
                ) {
                    if (count == 0) {
                        IconButton(
                            onClick = {
                                val newQuantity = count + 1
                                it.onAddProduct(product, newQuantity)
                            },
                            modifier = Modifier.clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Aumentar",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    } else {
                        ProductActionSection(quantity = count, onAddClick = {
                            val newQuantity = count + 1
                            it.onUpdateQuantityProduct(product, newQuantity)
                        }, onRemoveClick = {
                            if (count > 1) {
                                val newQuantity = count - 1
                                it.onUpdateQuantityProduct(product, newQuantity)
                            }
                            if (count == 1) {
                                it.onRemoveProduct(product)
                            }
                        })
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
fun PreviewItemProductCart() {
    MaterialTheme {
        val sampleProduct = Product(
            id = "1",
            name = "Manzanas Rojas Extra Frescas",
            unitMeasure = "X Kilogramo",
            unitPrice = "5.50",
            imageProduct = listOf(ImageProduct(filename = "")),
            cartItem = null
        )

        val fakeActions = object : ProductActions {
            override fun onAddProduct(product: Product, quantity: Int) {}
            override fun onUpdateQuantityProduct(product: Product, quantity: Int) {}
            override fun onRemoveProduct(product: Product) {}
        }

        Column(Modifier.background(Color.White)) {
            ItemProductCart(
                product = sampleProduct, productActions = fakeActions, onClick = {})
        }
    }
}

