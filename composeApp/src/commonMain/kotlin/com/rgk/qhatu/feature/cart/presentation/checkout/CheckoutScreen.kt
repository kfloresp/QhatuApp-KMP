package com.rgk.qhatu.feature.cart.presentation.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.chip.ChipGroup
import com.rgk.qhatu.common.components.textfield.ClickableTextField
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.cart.domain.model.CartSummary
import com.rgk.qhatu.feature.cart.presentation.checkout.component.ItemProductCheckout
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.payment.presentation.paymentform.ID_CONFIG_TYPE_PAYMENT_DEFAULT
import com.rgk.qhatu.feature.product.domain.model.toOperationDetail
import com.rgk.qhatu.feature.sale.domain.model.SaleWithOperation
import com.rgk.qhatu.feature.setting.domain.model.Configuration
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_cart_title
import qhatuapp.composeapp.generated.resources.tx_checkout_product
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

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
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
            errorText = ""
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
            Text(
                text = "${stringResource(Res.string.tx_checkout_product)} ${cartSummary?.itemCountSummary.orEmpty()}",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { product ->
                    ItemProductCheckout(product = product)
                }
            }
        }
    }
}