package com.rgk.qhatu.feature.product.presentation.productoform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.common.components.textfield.ClickableTextField
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.payment.presentation.paymentform.PATTERNS
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.Configuration
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_payment_amount
import qhatuapp.composeapp.generated.resources.tx_payment_currency_symbol

@Composable
fun ProductFormScreen(
    uiState: ProductFormUiState,
    isNew: Boolean = false,
    formState: ProductFormValidationState,
    onFieldChange: (Product.() -> Product) -> Unit,
    onSaveClick: (Product) -> Unit,
    onDeleteClick: (Product) -> Unit,
    onUnitMeasureClick: () -> Unit,
    onCategoryClick: () -> Unit,
    onBrandClick: () -> Unit,
    onStorageClick: () -> Unit,
    onClearUnitMeasure: () -> Unit,
    onClearCategory: () -> Unit,
    onClearBrand: () -> Unit,
    onClearStorage: () -> Unit,
    selectedUnitMeasure: UnitMeasure? = null,
    selectedCategory: Category? = null,
    selectedBrand: Brand? = null,
    selectedStorage: Configuration? = null,
    onBackPopUp: () -> Unit,
    onDeletePopUp: () -> Unit,
) {
    val fields = formState.fields
    when (uiState) {
        is ProductFormUiState.Error -> {
            ErrorSection(uiState.message)
        }

        ProductFormUiState.Loading -> {
            LoadingSection()
        }

        is ProductFormUiState.Success -> {
            val selectedUnitMeasureName = selectedUnitMeasure?.name.orEmpty()
            val selectedUnitMeasureId = selectedUnitMeasure?.id.orEmpty()
            if (selectedUnitMeasureId.isNotEmpty()) {
                onFieldChange {
                    copy(
                        unitMeasureId = selectedUnitMeasureId,
                        unitMeasure = selectedUnitMeasureName
                    )
                }
            }
            val selectedCategoryName = selectedCategory?.name.orEmpty()
            val selectedCategoryId = selectedCategory?.id.orEmpty()
            if (selectedCategoryId.isNotEmpty()) {
                onFieldChange {
                    copy(
                        categoryId = selectedCategoryId,
                        category = selectedCategoryName
                    )
                }
            }
            val selectedBrandName = selectedBrand?.name.orEmpty()
            val selectedBrandId = selectedBrand?.id.orEmpty()
            if (selectedBrandId.isNotEmpty()) {
                onFieldChange {
                    copy(
                        brandId = selectedBrandId,
                        brand = selectedBrandName
                    )
                }
            }
            val selectedStorageName = selectedStorage?.name.orEmpty()
            val selectedStorageId = selectedStorage?.id.orEmpty()
            if (selectedStorageId.isNotEmpty()) {
                onFieldChange {
                    copy(
                        storageTypeId = selectedStorageId,
                        storageType = selectedStorageName
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(12.dp)
                    .verticalScroll(rememberScrollState()).imePadding(),
            ) {
                Column {
                    CustomTextField(
                        value = fields.ean,
                        onValueChange = { newValue ->
                            onFieldChange {
                                copy(ean = newValue)
                            }
                        }, params = CustomTextFieldParams(
                            label = "Código EAN",
                            singleLine = true,
                            maxLength = 50,
                        )
                    )

                    CustomTextField(
                        value = fields.name,
                        onValueChange = { newValue ->
                            onFieldChange {
                                copy(name = newValue)
                            }
                        }, params = CustomTextFieldParams(
                            label = "Nombre del producto",
                            singleLine = true,
                            maxLength = 50,
                        )
                    )

                    CustomTextField(
                        value = fields.unitPrice, onValueChange = { newValue ->
                            if (newValue.isEmpty()) {
                                onFieldChange {
                                    copy(unitPrice = "")
                                }
                                return@CustomTextField
                            }
                            val regex = Regex(PATTERNS)
                            if (newValue.matches(regex)) {
                                onFieldChange {
                                    copy(unitPrice = newValue)
                                }
                            }
                        }, params = CustomTextFieldParams(
                            label = stringResource(Res.string.tx_payment_amount),
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
                    ClickableTextField(
                        selectedText = selectedStorageName,
                        "Tipo almacenamiento",
                        onClick = {
                            onStorageClick.invoke()
                        },
                        onClear = {
                            onClearStorage.invoke()
                        }
                    )
                    ClickableTextField(
                        selectedText = selectedUnitMeasureName,
                        "Unidad medida",
                        onClick = {
                            onUnitMeasureClick.invoke()
                        },
                        onClear = {
                            onClearUnitMeasure.invoke()
                        }
                    )
                    ClickableTextField(
                        selectedText = selectedCategoryName,
                        "Categoria",
                        onClick = {
                            onCategoryClick.invoke()
                        },
                        onClear = {
                            onClearCategory.invoke()
                        }
                    )
                    ClickableTextField(
                        selectedText = selectedBrandName,
                        "Marca",
                        onClick = {
                            onBrandClick.invoke()
                        },
                        onClear = {
                            onClearBrand.invoke()
                        }
                    )
                }

                if (isNew) {
                    ButtonActions(
                        modifier = Modifier.padding(12.dp),
                        primaryButtonText = stringResource(Res.string.tx_global_save_changes),
                        isEnabled = formState.isValid,
                        onPrimaryClick = {
                            onSaveClick(fields)
                        },
                    )
                } else {
                    ButtonActions(
                        modifier = Modifier.padding(12.dp),
                        primaryButtonText = stringResource(Res.string.tx_global_save_changes),
                        isEnabled = formState.isValid,
                        onPrimaryClick = {
                            onSaveClick(fields)
                        },
                        secondaryButtonText = stringResource(Res.string.tx_global_delete_changes),
                        onSecondaryClick = { onDeleteClick(fields.copy(isDeleted = true)) })
                }
            }
        }

        is ProductFormUiState.SuccessUpsert -> {
            val isDeleted = uiState.isDeleted
            if (isDeleted) {
                onDeletePopUp()
            } else {
                onBackPopUp()
            }
        }
    }

}