package com.rgk.qhatu.feature.cart.presentation.cart.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CartOptions(
    options: List<CartOption> = CartOption.entries,
    onOptionClick: (CartOption) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp),
    ) {
        options.forEachIndexed { index, option ->
            Text(
                text = option.label,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionClick(option) }
                    .padding(vertical = 12.dp, horizontal = 24.dp)
            )
            if (index < options.lastIndex) {
                HorizontalDivider(thickness = 1.dp)
            }
        }
    }
}

enum class CartOption(val label: String) {
    DELETE_ITEM("Eliminar producto"),
    DELETE_ALL("Eliminar todos (Vaciar carrito)"),
    SAVE_CART("Guardar carrito (Ver más tarde)")
}