package com.rgk.qhatu.ui.components.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rgk.qhatu.ui.components.cart.CartIconWithBadge
import com.rgk.qhatu.utils.QhatuTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

const val CURRENCY = "S/."

@Composable
fun QhatuCartToolbar(
    title: String,
    cartValue: Double,
    cartItemCount: Int,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    onNavigationClick: () -> Unit,
    onCartClick: () -> Unit,
) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = elevation,
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 12.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .clickable(onClick = onCartClick)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "$CURRENCY $cartValue",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.width(8.dp))
                VerticalDivider( Modifier
                    .height(24.dp)
                    .width(1.dp),thickness = 1.dp)
                Spacer(Modifier.width(8.dp))
                CartIconWithBadge(itemCount = cartItemCount)
            }
        }
    }
}

@Preview()
@Composable
fun CartToolbarPreview() {
    QhatuTheme {
        QhatuCartToolbar(
            title = "Búsqueda",
            cartValue = 10.0,
            cartItemCount = 100,
            onNavigationClick = { /* Acción de volver */ },
            onCartClick = { /* Acción de ir al carrito */ }
        )
    }
}
