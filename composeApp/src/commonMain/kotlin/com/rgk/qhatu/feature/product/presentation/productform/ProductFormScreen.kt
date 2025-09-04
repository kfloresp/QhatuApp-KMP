package com.rgk.qhatu.feature.product.presentation.productform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.error.ErrorSection
import com.rgk.qhatu.common.components.imagepicker.ImagePicker
import com.rgk.qhatu.common.components.loading.LoadingSection
import com.rgk.qhatu.common.components.textfield.ClickableTextField
import com.rgk.qhatu.common.components.textfield.CustomTextField
import com.rgk.qhatu.common.components.textfield.CustomTextFieldParams
import com.rgk.qhatu.feature.payment.presentation.paymentform.PATTERNS
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.model.StorageType
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete_photo_message
import qhatuapp.composeapp.generated.resources.tx_global_confirmation
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_payment_currency_symbol
import qhatuapp.composeapp.generated.resources.tx_product_brand
import qhatuapp.composeapp.generated.resources.tx_product_category
import qhatuapp.composeapp.generated.resources.tx_product_code
import qhatuapp.composeapp.generated.resources.tx_product_has_batch
import qhatuapp.composeapp.generated.resources.tx_product_is_active
import qhatuapp.composeapp.generated.resources.tx_product_name
import qhatuapp.composeapp.generated.resources.tx_product_photos
import qhatuapp.composeapp.generated.resources.tx_product_price
import qhatuapp.composeapp.generated.resources.tx_product_storage
import qhatuapp.composeapp.generated.resources.tx_product_unit

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
    selectedStorage: StorageType? = null,
    onBackPopUp: () -> Unit,
    onImagePickerClick: () -> Unit,
) {
    var selectedImageProduct by remember { mutableStateOf<ImageProduct?>(null) }
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
            val selectedStorageId = selectedStorage?.value.orEmpty()
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
                            label = stringResource(Res.string.tx_product_code),
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
                            label = stringResource(Res.string.tx_product_name),
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
                            label = stringResource(Res.string.tx_product_price),
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
                        selectedText = formState.fields.storageType,
                        stringResource(Res.string.tx_product_storage),
                        onClick = {
                            onStorageClick.invoke()
                        },
                        onClear = {
                            onClearStorage.invoke()
                        }
                    )
                    ClickableTextField(
                        selectedText = formState.fields.unitMeasure,
                        stringResource(Res.string.tx_product_unit),
                        onClick = {
                            onUnitMeasureClick.invoke()
                        },
                        onClear = {
                            onClearUnitMeasure.invoke()
                        }
                    )
                    ClickableTextField(
                        selectedText = formState.fields.category,
                        stringResource(Res.string.tx_product_category),
                        onClick = {
                            onCategoryClick.invoke()
                        },
                        onClear = {
                            onClearCategory.invoke()
                        }
                    )
                    ClickableTextField(
                        selectedText = formState.fields.brand,
                        stringResource(Res.string.tx_product_brand),
                        onClick = {
                            onBrandClick.invoke()
                        },
                        onClear = {
                            onClearBrand.invoke()
                        }
                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = fields.isBatch,
                            onCheckedChange = { isChecked ->
                                onFieldChange {
                                    copy(isBatch = isChecked)
                                }
                            },
                        )
                        Text(
                            text = stringResource(Res.string.tx_product_has_batch),
                        )
                    }
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = fields.isActive,
                            onCheckedChange = { isChecked ->
                                onFieldChange {
                                    copy(isActive = isChecked)
                                }
                            },
                        )
                        Text(
                            text = stringResource(Res.string.tx_product_is_active),
                        )
                    }
                    Text(
                        text = stringResource(Res.string.tx_product_photos),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    ImagePicker(
                        images = fields.imageProduct,
                        onDeleteClick = { imageToDelete ->
                            selectedImageProduct = imageToDelete
                        }, onUploadClick = {
                            onImagePickerClick()
                        },
                        title = "Agregar fotos",
                        description = "Muestra tu producto desde diferentes ángulos",
                        buttonText = "Añadir fotos"
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
            onBackPopUp()
        }
    }

    selectedImageProduct?.let { imageDelete ->
        ConfirmDialog(
            title = stringResource(Res.string.tx_global_confirmation),
            description = stringResource(
                Res.string.tx_global_confirm_delete_photo_message
            ),
            primaryButtonText = stringResource(Res.string.tx_global_confirm_delete),
            onPrimaryClick = {
                onFieldChange {
                    copy(imageProduct = imageProduct.filter { it.filename != imageDelete.filename })
                }
                selectedImageProduct = null
            },
            secondaryButtonText = stringResource(Res.string.tx_global_cancel),
            onSecondaryClick = { selectedImageProduct = null },
            onDismiss = {
                selectedImageProduct = null
            }
        )
    }

}