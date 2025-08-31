package com.rgk.qhatu.feature.cart.presentation.checkout.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.presentation.checkout.SectionSummary
import com.rgk.qhatu.feature.sale.domain.model.Sale
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_checkout_cash_amount
import qhatuapp.composeapp.generated.resources.tx_checkout_cash_return
import qhatuapp.composeapp.generated.resources.tx_checkout_confirm_sale
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal_discount
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal_igv
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal_total
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_confirmation
import qhatuapp.composeapp.generated.resources.tx_payment_method

@Composable
fun SectionSummaryCheckout(
    sale: Sale,
    cartSummary: CartSummary?,
    onPrimaryClick: () -> Unit,
    onSecondaryClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.tx_global_confirmation),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        if (cartSummary?.hasSubtotalWithIgv ?: false) {
            SectionSummary(
                name = stringResource(Res.string.tx_checkout_subtotal_igv),
                amount = cartSummary.subTotalWithIgvSummary
            )
        }
        if (cartSummary?.hasSubtotalDiscount ?: false) {
            SectionSummary(
                name = stringResource(Res.string.tx_checkout_subtotal_discount),
                amount = "-${cartSummary.subtotalDiscountSummary}",
                color = MaterialTheme.colorScheme.primary,
            )
        }
        if (cartSummary?.hasTotal ?: false) {
            SectionSummary(
                name = stringResource(Res.string.tx_checkout_subtotal_total),
                amount = cartSummary.totalSummary
            )
        }

        HorizontalDivider(thickness = 1.dp)

        SectionSummary(
            name = stringResource(Res.string.tx_payment_method),
            amount = sale.paymentMethod.value
        )

        SectionSummary(
            name = stringResource(Res.string.tx_checkout_cash_amount),
            amount = sale.amountPaidFormatted
        )

        SectionSummary(
            name = stringResource(Res.string.tx_checkout_cash_return),
            amount = sale.changeReturnedFormatted
        )

        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            Button(onClick = {
                onPrimaryClick()
            }, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(Res.string.tx_checkout_confirm_sale))
            }
            OutlinedButton(
                onClick = {
                    onSecondaryClick()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.tx_global_cancel))
            }
        }
    }
}