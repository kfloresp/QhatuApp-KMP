package com.rgk.qhatu.feature.product.presentation.productoform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.feature.product.domain.model.Product
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_product_brand
import qhatuapp.composeapp.generated.resources.tx_product_category
import qhatuapp.composeapp.generated.resources.tx_product_code
import qhatuapp.composeapp.generated.resources.tx_product_edit
import qhatuapp.composeapp.generated.resources.tx_product_has_batch
import qhatuapp.composeapp.generated.resources.tx_product_is_active
import qhatuapp.composeapp.generated.resources.tx_product_name
import qhatuapp.composeapp.generated.resources.tx_product_no
import qhatuapp.composeapp.generated.resources.tx_product_price
import qhatuapp.composeapp.generated.resources.tx_product_storage
import qhatuapp.composeapp.generated.resources.tx_product_unit
import qhatuapp.composeapp.generated.resources.tx_product_yes

@Composable
fun ProductViewerScreen(
    uiState: ProductFormUiState,
    onDeleteClick: (Product) -> Unit,
    onEditClick: (Product) -> Unit,
    onDeletePopUp: () -> Unit,
) {
    when (uiState) {
        is ProductFormUiState.Error -> {
            ErrorSection(uiState.message)
        }

        ProductFormUiState.Loading -> {
            LoadingSection()
        }

        is ProductFormUiState.Success -> {
            val product = uiState.result
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(Res.string.tx_product_name),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(Res.string.tx_product_code),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = product.ean,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(Res.string.tx_product_price),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = product.unitPriceValue,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(Res.string.tx_product_unit),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = product.unitMeasure,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(Res.string.tx_product_category),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = product.category,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(Res.string.tx_product_brand),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = product.brand,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(Res.string.tx_product_storage),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = product.storageType,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "${stringResource(Res.string.tx_product_has_batch)}: ${
                            if (product.isActive) stringResource(
                                Res.string.tx_product_yes
                            ) else stringResource(Res.string.tx_product_no)
                        }",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "${stringResource(Res.string.tx_product_is_active)}: ${
                            if (product.isActive) stringResource(
                                Res.string.tx_product_yes
                            ) else stringResource(Res.string.tx_product_no)
                        }",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                ButtonActions(
                    modifier = Modifier.padding(12.dp).wrapContentHeight(),
                    primaryButtonText = stringResource(Res.string.tx_global_delete_changes),
                    isEnabled = true,
                    onPrimaryClick = {
                        onDeleteClick(product)
                    },
                    secondaryButtonText = stringResource(Res.string.tx_product_edit),
                    onSecondaryClick = {
                        onEditClick(product)
                    })
            }
        }

        is ProductFormUiState.SuccessUpsert -> {
            onDeletePopUp.invoke()
        }
    }
}