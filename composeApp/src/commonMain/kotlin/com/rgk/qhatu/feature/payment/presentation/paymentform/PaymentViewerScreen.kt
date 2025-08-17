package com.rgk.qhatu.feature.payment.presentation.paymentform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.datepicker.toFormattedDate
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_payment_amount
import qhatuapp.composeapp.generated.resources.tx_payment_comment
import qhatuapp.composeapp.generated.resources.tx_payment_customer_data
import qhatuapp.composeapp.generated.resources.tx_payment_date
import qhatuapp.composeapp.generated.resources.tx_payment_method
import qhatuapp.composeapp.generated.resources.tx_payment_operation_number
import qhatuapp.composeapp.generated.resources.tx_payment_payment_details
import qhatuapp.composeapp.generated.resources.tx_payment_type

@Composable
fun PaymentViewerScreen(uiState: PaymentFormUiState) {
    when (uiState) {
        is PaymentFormUiState.Error -> {
            ErrorSection(uiState.message)
        }

        PaymentFormUiState.Loading -> {
            LoadingSection()
        }

        is PaymentFormUiState.Success -> {
            val payment = uiState.result
            Column(
                modifier = Modifier.fillMaxWidth().padding(12.dp)
                    .verticalScroll(rememberScrollState()).imePadding(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(Res.string.tx_payment_customer_data),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = payment.customer,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(Res.string.tx_payment_payment_details),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = stringResource(
                        Res.string.tx_payment_date,
                        payment.paymentDate.toFormattedDate()
                    ),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${stringResource(Res.string.tx_payment_amount)}: ${payment.amountPaidWithCurrency}",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                if (payment.comments.isNotEmpty()) {
                    Text(
                        text = stringResource(Res.string.tx_payment_comment, payment.comments),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = stringResource(Res.string.tx_payment_method),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                if (payment.paymentMethod.isNotEmpty()) {
                    Text(
                        text = stringResource(Res.string.tx_payment_type, payment.paymentMethod),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }
                if (payment.numberOperation.isNotEmpty()) {
                    Text(
                        text = stringResource(
                            Res.string.tx_payment_operation_number,
                            payment.numberOperation
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        is PaymentFormUiState.SuccessUpsert -> Unit
    }
}