package com.rgk.qhatu.feature.cart.presentation.checkout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rgk.qhatu.common.components.chip.ChipGroup
import com.rgk.qhatu.common.components.textfield.ClickableTextField
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.presentation.checkout.component.ItemProductCheckout
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.payment.presentation.paymentform.ID_CONFIG_TYPE_PAYMENT_DEFAULT
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.model.toOperationDetail
import com.rgk.qhatu.feature.sale.domain.model.SaleVaucherType
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation
import com.rgk.qhatu.feature.setting.domain.model.Configuration
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_cart_title
import qhatuapp.composeapp.generated.resources.tx_checkout_product
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal_discount
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal_igv
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal_total
import qhatuapp.composeapp.generated.resources.tx_checkout_title
import qhatuapp.composeapp.generated.resources.tx_payment_method
import qhatuapp.composeapp.generated.resources.tx_payment_operation_number_optional
import qhatuapp.composeapp.generated.resources.tx_payment_select_customer

@Composable
fun CheckoutScreen(
    uiState: CheckoutUiState,
    formState: CheckoutFormValidationState,
    onFieldChange: (SaleWithOperation.() -> SaleWithOperation) -> Unit,
    onClearCustomer: () -> Unit,
    selectedCustomer: Customer? = null,
    onCustomerClick: () -> Unit,
    methodPayments: List<Configuration>,
    cartSummary: CartSummary?,
    onBackPopUp: () -> Unit,
    setLoading: (Boolean) -> Unit,
) {
    val fields = formState.fields
    val selectedName = selectedCustomer?.nameCustomer.orEmpty()
    val vouchers: List<SaleVaucherType> = SaleVaucherType.entries

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        ClickableTextField(
            selectedText = selectedName,
            stringResource(Res.string.tx_payment_select_customer),
            onClick = {
                onCustomerClick.invoke()
            },
            onClear = {
                onClearCustomer.invoke()
            },
        )

        Text(
            text = stringResource(Res.string.tx_payment_method),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ChipGroup(
            items = methodPayments,
            keySelector = { it.id },
            valueSelector = { it.name },
            selectedKey = fields.sale.paymentMethodId,
            onChipClick = { methodPayment ->
                onFieldChange {
                    copy(
                        sale = sale.copy(paymentMethodId = methodPayment.id)
                    )
                }
            },
        )

        Text(
            text = "Comprobante",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ChipGroup(
            items = vouchers,
            keySelector = { it.name },
            valueSelector = { it.value },
            selectedKey = fields.sale.vaucherType.name,
            onChipClick = { voucherType ->
                onFieldChange {
                    copy(
                        sale = sale.copy(vaucherType = voucherType)
                    )
                }
            },
        )

        if (fields.sale.paymentMethodId != ID_CONFIG_TYPE_PAYMENT_DEFAULT && fields.sale.paymentMethodId.isNotEmpty()) {
            CustomTextField(
                value = fields.sale.paymentOperationNo.orEmpty(), onValueChange = {
                    onFieldChange {
                        copy(
                            sale = sale.copy(paymentOperationNo = it)
                        )
                    }
                }, params = CustomTextFieldParams(
                    label = stringResource(Res.string.tx_payment_operation_number_optional),
                    singleLine = true,
                    maxLength = 10,
                )
            )
        }

        setLoading(uiState is CheckoutUiState.Loading)

        if (uiState is CheckoutUiState.Error || uiState is CheckoutUiState.Empty) {
            onBackPopUp()
        }

        if (uiState is CheckoutUiState.Success) {
            val items = uiState.result
            LaunchedEffect(items) {
                onFieldChange {
                    copy(
                        details = items.map { it.toOperationDetail() }
                    )
                }
            }
            SectionItemProduct(items, cartSummary)
            cartSummary?.let {
                SectionCartSummary(it)
            }
        }
    }
}

@Composable
private fun SectionItemProduct(items: List<Product>, cartSummary: CartSummary?) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "${stringResource(Res.string.tx_checkout_product)} ${cartSummary?.itemCountSummary.orEmpty()}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = if (expanded) "Collapse" else "Expand"
            )
        }
    }

    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { product ->
                ItemProductCheckout(product = product)
            }
        }
    }
}

@Composable
private fun SectionCartSummary(cartSummary: CartSummary) {
    var expanded by remember { mutableStateOf(true) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = stringResource(Res.string.tx_checkout_subtotal),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = if (expanded) "Collapse" else "Expand"
            )
        }
    }

    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (cartSummary.subtotalWithIgv > 0) {
                SectionSummary(
                    name = stringResource(Res.string.tx_checkout_subtotal_igv),
                    amount = cartSummary.subTotalWithIgvSummary
                )
            }
            if (cartSummary.totalDiscount > 0) {
                SectionSummary(
                    name = stringResource(Res.string.tx_checkout_subtotal_discount),
                    amount = "-${cartSummary.totalDiscountSummary}",
                    color = Color.Green,
                )
            }
            if (cartSummary.total > 0) {
                SectionSummary(
                    name = stringResource(Res.string.tx_checkout_subtotal_total),
                    amount = cartSummary.totalSummary
                )
            }
        }
    }
}

@Composable
private fun SectionSummary(name: String, amount: String, color: Color = Color.Unspecified) {
    Row(Modifier.fillMaxWidth()) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
            color = color
        )
        Text(
            text = amount,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            modifier = Modifier.wrapContentWidth(),
            color = color
        )
    }
}