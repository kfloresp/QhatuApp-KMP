package com.rgk.qhatu.feature.product.presentation.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.theme.QhatuTheme
import com.rgk.qhatu.feature.product.domain.model.Product
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ItemProductAction(
    modifier: Modifier = Modifier,
    product: Product,
    imageVector: ImageVector = Icons.Default.Call,
    onActionClick: () -> Unit,
    onItemClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black.copy(alpha = 0.05f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "P",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.nombre,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = product.precio_unitario.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            IconButton(onClick = onActionClick, modifier = Modifier.size(48.dp)) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
private fun ItemActionPreview() {
    QhatuTheme {
        Column(modifier = Modifier.background(Color.White)) {
            ItemProductAction(
                product = Product(
                    id = "1",
                    ean = "123123",
                    nombre = "Coca cola",
                    precio_unitario = 3.0,
                    unidad_medida_id = "1"
                ),
                onItemClick = {},
                onActionClick = {},
            )
        }
    }
}