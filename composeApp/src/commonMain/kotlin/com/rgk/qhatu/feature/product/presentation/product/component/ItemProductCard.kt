package com.rgk.qhatu.feature.product.presentation.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.rgk.qhatu.feature.product.domain.model.Product
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.image_place_holder

@Composable
fun ItemProductCard(
    product: Product,
    onClick: (Product) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .clickable { onClick(product) }
            .padding(8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(12.dp)
            )
    ) {

        AsyncImage(
            model = product.imageProduct.firstOrNull()?.filename ?: "",
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .height(150.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp)),
            placeholder = painterResource(resource = Res.drawable.image_place_holder),
            error = painterResource(resource = Res.drawable.image_place_holder)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
        )

        Text(
            text = product.unitMeasure,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = product.unitPriceValue, style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            ), modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview()
@Composable
fun ProductItemCardPreview() {
    val sampleProduct = Product(
        id = "1",
        ean = "123456789",
        name = "Leche Gloria Entera Evaporada",
        brand = "Gloria",
        unitPrice = "4.50",
        unitMeasure = "Lata 400ml"
    )
    QhatuTheme {
        Column(Modifier.background(color = Color.White)) {
            ItemProductCard(
                product = sampleProduct
            )
        }
    }
}
