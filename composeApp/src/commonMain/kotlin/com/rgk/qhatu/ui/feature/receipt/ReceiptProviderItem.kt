package com.rgk.qhatu.ui.feature.receipt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.domain.model.Client
import com.rgk.qhatu.domain.model.Product

@Composable
fun ReceiptProviderItem(
    product: Client,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.5f),
                modifier = Modifier.size(96.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = product.nombre.orEmpty(),
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text("Código Proveedor: ${product.id}", style = MaterialTheme.typography.titleMedium)
        Text("Dirección: ${product.direccion}", style = MaterialTheme.typography.titleMedium)
        Text("Teléfono: ${product.celular}", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(10.dp))

        val isActivo = product.flagActivo != 0
        val statusColor = if (isActivo) Color(0xFF4CAF50) else Color(0xFFD32F2F)
        val statusText = if (isActivo) "Activo" else "Inactivo"

        Box(
            modifier = Modifier
                .background(statusColor.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = statusText,
                style = MaterialTheme.typography.bodySmall,
                color = statusColor
            )
        }
    }
}
