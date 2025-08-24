package com.rgk.qhatu.common.components.bottombar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_cart_confirm
import qhatuapp.composeapp.generated.resources.tx_cart_total_summary

@Composable
fun CartSummarySection(
    shoppingCartTotal: String,
    onShoppingCartClick: () -> Unit,
    isEnabled: Boolean = true,
) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f).padding(horizontal = 15.dp)) {
            Text(
                text = stringResource(Res.string.tx_cart_total_summary),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = shoppingCartTotal,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                textAlign = TextAlign.Start
            )
        }
        Button(onClick = onShoppingCartClick, enabled = isEnabled) {
            Text(stringResource(Res.string.tx_cart_confirm))
        }
    }
}