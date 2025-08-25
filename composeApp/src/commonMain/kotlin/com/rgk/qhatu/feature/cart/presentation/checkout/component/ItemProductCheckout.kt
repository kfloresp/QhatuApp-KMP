package com.rgk.qhatu.feature.cart.presentation.checkout.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rgk.qhatu.common.theme.QhatuTheme
import com.rgk.qhatu.feature.cart.domain.model.CartItem
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import com.rgk.qhatu.feature.product.domain.model.Product
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.image_place_holder
import qhatuapp.composeapp.generated.resources.tx_cart_total_text

@Composable
fun ItemProductCheckout(
    product: Product,
) {
    val cartItem = product.cartItem
    val count = cartItem?.quantity ?: 0
    val unitPriceAmount = cartItem?.unitPriceAmount.orEmpty()
    val totalPriceAmount = cartItem?.totalPriceAmount.orEmpty()

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(12.dp)
            ).padding(8.dp)
    ) {
        Row {
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
            Column {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$count ${product.unitMeasure} X $unitPriceAmount",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.outline,
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${stringResource(Res.string.tx_cart_total_text)} $totalPriceAmount",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    ),
                )

                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Preview()
@Composable
fun PreviewItemProductCart() {
    QhatuTheme {
        val sampleProduct = Product(
            id = "1",
            name = "Leche Gloria Azul Lata 400Gr",
            unitMeasure = "Unidades",
            unitPrice = "3.40",
            imageProduct = listOf(ImageProduct(filename = "")),
            cartItem = CartItem(productId = "1", quantity = 37, unitPrice = 3.4)
        )

        Column(Modifier.background(Color.White)) {
            ItemProductCheckout(
                product = sampleProduct
            )
        }
    }
}