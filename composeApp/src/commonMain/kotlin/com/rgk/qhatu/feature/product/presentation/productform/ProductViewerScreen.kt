package com.rgk.qhatu.feature.product.presentation.productform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.carousel.ImageCarousel
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.feature.product.domain.model.Product
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_product_brand
import qhatuapp.composeapp.generated.resources.tx_product_category
import qhatuapp.composeapp.generated.resources.tx_product_code
import qhatuapp.composeapp.generated.resources.tx_product_details_title
import qhatuapp.composeapp.generated.resources.tx_product_edit
import qhatuapp.composeapp.generated.resources.tx_product_has_batch
import qhatuapp.composeapp.generated.resources.tx_product_is_active
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
                ImageCarousel(product.imageProduct)
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                SectionHeader(text = stringResource(Res.string.tx_product_details_title))

                DetailRow(
                    label = stringResource(Res.string.tx_product_code),
                    value = product.ean
                )
                DetailRow(
                    label = stringResource(Res.string.tx_product_price),
                    value = product.unitPriceValue,
                    valueEmphasis = true
                )
                DetailRow(
                    label = stringResource(Res.string.tx_product_unit),
                    value = product.unitMeasure
                )
                DetailRow(
                    label = stringResource(Res.string.tx_product_category),
                    value = product.category
                )
                DetailRow(
                    label = stringResource(Res.string.tx_product_brand),
                    value = product.brand
                )
                DetailRow(
                    label = stringResource(Res.string.tx_product_storage),
                    value = product.storageType
                )
                DetailRow(
                    label = stringResource(Res.string.tx_product_has_batch),
                    value = if (product.isBatch)
                        stringResource(Res.string.tx_product_yes)
                    else
                        stringResource(Res.string.tx_product_no)
                )
                DetailRow(
                    label = stringResource(Res.string.tx_product_is_active),
                    value = if (product.isActive)
                        stringResource(Res.string.tx_product_yes)
                    else
                        stringResource(Res.string.tx_product_no)
                )
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

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 2.dp)
    )
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
    )
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    valueEmphasis: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = if (valueEmphasis)
                MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            else
                MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}