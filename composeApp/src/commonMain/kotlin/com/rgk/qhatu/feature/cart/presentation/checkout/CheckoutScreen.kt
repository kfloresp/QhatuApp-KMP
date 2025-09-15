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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.chip.ChipGroup
import com.rgk.qhatu.common.components.textfield.ClickableTextField
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.presentation.checkout.component.ItemProductCheckout
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.payment.presentation.paymentform.PATTERNS
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.model.toOperationDetail
import com.rgk.qhatu.feature.sale.domain.model.SaleAmountCash
import com.rgk.qhatu.feature.sale.domain.model.SalePaymentMethod
import com.rgk.qhatu.feature.sale.domain.model.SaleVoucherType
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_checkout_amount_cash
import qhatuapp.composeapp.generated.resources.tx_checkout_cash_all
import qhatuapp.composeapp.generated.resources.tx_checkout_product
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal_discount
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal_igv
import qhatuapp.composeapp.generated.resources.tx_checkout_subtotal_total
import qhatuapp.composeapp.generated.resources.tx_checkout_voucher
import qhatuapp.composeapp.generated.resources.tx_payment_currency_symbol
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
    cartSummary: CartSummary?,
    onBackPopUp: () -> Unit,
    navigateToHome: () -> Unit,
    setLoading: (Boolean) -> Unit,
) {
    val fields = formState.fields
    val selectedName = selectedCustomer?.email.orEmpty()
    val selectedId = selectedCustomer?.customerId.orEmpty()
    val vouchers: List<SaleVoucherType> = SaleVoucherType.entries
    val methodPayments: List<SalePaymentMethod> = SalePaymentMethod.entries
    val saleAmountCash: List<SaleAmountCash> = SaleAmountCash.entries

    if (selectedId.isNotEmpty()) {
        onFieldChange {
            copy(
                sale = sale.copy(customerId = selectedId)
            )
        }
    }

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
            keySelector = { it.name },
            valueSelector = { it.value },
            selectedKey = fields.sale.paymentMethod.name,
            onChipClick = { methodPayment ->
                onFieldChange {
                    copy(
                        sale = sale.copy(paymentMethod = methodPayment)
                    )
                }
            },
        )
        when (fields.sale.paymentMethod) {
            SalePaymentMethod.CASH -> {
                onFieldChange {
                    copy(
                        sale = sale.copy(
                            paymentOperationNo = "",
                        )
                    )
                }
                SectionAmountCash(saleAmountCash, onFieldChange, fields)
            }

            SalePaymentMethod.YAPE, SalePaymentMethod.PLIN -> {
                CustomTextField(
                    value = fields.sale.paymentOperationNo.orEmpty(), onValueChange = {
                        onFieldChange {
                            copy(
                                sale = sale.copy(
                                    paymentOperationNo = it,
                                    amountPaid = cartSummary?.total.toString(),
                                    changeReturned = 0.0
                                )
                            )
                        }
                    }, params = CustomTextFieldParams(
                        label = stringResource(Res.string.tx_payment_operation_number_optional),
                        singleLine = true,
                        maxLength = 10,
                        modifier = Modifier.wrapContentWidth()
                    )
                )
            }

            SalePaymentMethod.CREDIT -> {
                onFieldChange {
                    copy(
                        sale = sale.copy(
                            paymentOperationNo = "",
                            amountPaid = cartSummary?.total.toString(),
                            changeReturned = 0.0
                        )
                    )
                }
            }
        }

        Text(
            text = stringResource(Res.string.tx_checkout_voucher),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ChipGroup(
            items = vouchers,
            keySelector = { it.name },
            valueSelector = { it.value },
            selectedKey = fields.sale.voucherType.name,
            onChipClick = { voucherType ->
                onFieldChange {
                    copy(
                        sale = sale.copy(voucherType = voucherType)
                    )
                }
            },
        )

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

        if (uiState is CheckoutUiState.SuccessSave) {
            navigateToHome()
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
                contentDescription = null
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
private fun SectionAmountCash(
    saleAmountCash: List<SaleAmountCash>,
    onFieldChange: (SaleWithOperation.() -> SaleWithOperation) -> Unit,
    fields: SaleWithOperation,
) {
    var selectedKey by remember { mutableStateOf<SaleAmountCash?>(null) }
    Text(
        text = stringResource(Res.string.tx_checkout_cash_all),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(vertical = 8.dp)
    )

    ChipGroup(
        items = saleAmountCash,
        keySelector = { it.name },
        valueSelector = {
            when (it) {
                SaleAmountCash.OTHER -> it.value
                else -> {
                    "S/.${it.value}"
                }
            }
        },
        selectedKey = selectedKey?.name.orEmpty(),
        onChipClick = { amountCash ->
            selectedKey = amountCash
            if (amountCash != SaleAmountCash.OTHER) {
                onFieldChange {
                    copy(
                        sale = sale.copy(amountPaid = amountCash.value)
                    )
                }
            } else {
                onFieldChange {
                    copy(
                        sale = sale.copy(amountPaid = null)
                    )
                }
            }
        },
    )
    selectedKey?.let {
        if (selectedKey == SaleAmountCash.OTHER) {
            CustomTextField(
                value = fields.sale.amountPaid.orEmpty(), onValueChange = { newValue ->
                    val regex = Regex(PATTERNS)

                    if (newValue.isEmpty()) {
                        onFieldChange {
                            copy(sale = sale.copy(amountPaid = "", changeReturned = 0.0))
                        }
                        return@CustomTextField
                    }

                    if (!newValue.matches(regex)) {
                        return@CustomTextField
                    }

                    val paid = newValue.toDoubleOrNull()
                    val change = if (paid != null && paid > fields.sale.grandTotal) {
                        paid - fields.sale.grandTotal
                    } else {
                        0.0
                    }

                    onFieldChange {
                        copy(
                            sale = sale.copy(
                                amountPaid = newValue,
                                changeReturned = change
                            )
                        )
                    }

                }, params = CustomTextFieldParams(
                    label = stringResource(Res.string.tx_checkout_amount_cash),
                    singleLine = true,
                    maxLength = 6,
                    leadingIcon = {
                        Text(stringResource(Res.string.tx_payment_currency_symbol))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            )
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
                contentDescription = null
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
            if (cartSummary.hasSubtotalWithIgv) {
                SectionSummary(
                    name = stringResource(Res.string.tx_checkout_subtotal_igv),
                    amount = cartSummary.subTotalWithIgvSummary
                )
            }
            if (cartSummary.hasSubtotalDiscount) {
                SectionSummary(
                    name = stringResource(Res.string.tx_checkout_subtotal_discount),
                    amount = "-${cartSummary.subtotalDiscountSummary}",
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            if (cartSummary.hasTotal) {
                SectionSummary(
                    name = stringResource(Res.string.tx_checkout_subtotal_total),
                    amount = cartSummary.totalSummary
                )
            }
        }
    }
}

@Composable
fun SectionSummary(name: String, amount: String, color: Color = Color.Unspecified) {
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