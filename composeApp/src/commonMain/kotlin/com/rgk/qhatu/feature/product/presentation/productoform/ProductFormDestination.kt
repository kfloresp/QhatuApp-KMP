package com.rgk.qhatu.feature.product.presentation.productoform

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.bottomsheet.CustomBottomSheet
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.search.SearchContent
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.Configuration
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_cancel
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete
import qhatuapp.composeapp.generated.resources.tx_global_confirm_delete_register_message
import qhatuapp.composeapp.generated.resources.tx_global_confirm_save
import qhatuapp.composeapp.generated.resources.tx_global_confirm_save_subtitle
import qhatuapp.composeapp.generated.resources.tx_global_confirmation
import qhatuapp.composeapp.generated.resources.tx_product_new_title
import qhatuapp.composeapp.generated.resources.tx_product_view_title

@Serializable
data class ProductFormDestination(val productId: String)

@OptIn(ExperimentalComposeUiApi::class)
internal fun NavGraphBuilder.productFormDestination(
    onBackPopUp: () -> Unit,
    onDeletePopUp: () -> Unit,
) {
    composable<ProductFormDestination> { destination ->
        val viewModel: ProductFormViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isNewProduct by viewModel.isNewProduct.collectAsState()
        val formState by viewModel.formState.collectAsState()
        val categoryList by viewModel.categoryList.collectAsState()
        val brandList by viewModel.brandList.collectAsState()
        val storageList by viewModel.storageList.collectAsState()
        val unitMeasureList by viewModel.unitMeasureList.collectAsState()

        var selectedProductToDelete by remember { mutableStateOf<Product?>(null) }
        var selectedProductToSave by remember { mutableStateOf<Product?>(null) }
        var selectedCategoryToClick by remember { mutableStateOf(false) }
        var selectedBrandToClick by remember { mutableStateOf(false) }
        var selectedStorageToClick by remember { mutableStateOf(false) }
        var selectedUnitMeasureToClick by remember { mutableStateOf(false) }
        var selectedCategory by remember { mutableStateOf<Category?>(null) }
        var selectedBrand by remember { mutableStateOf<Brand?>(null) }
        var selectedStorage by remember { mutableStateOf<Configuration?>(null) }
        var selectedUnitMeasure by remember { mutableStateOf<UnitMeasure?>(null) }

        var queryCategory by remember { mutableStateOf("") }
        var queryBrand by remember { mutableStateOf("") }
        var queryUnitMeasure by remember { mutableStateOf("") }
        var queryStorage by remember { mutableStateOf("") }
        val isEditing by viewModel.isEditing.collectAsState()

        ProvideAppBar(
            title = if (isNewProduct) stringResource(Res.string.tx_product_new_title)
            else stringResource(Res.string.tx_product_view_title),
            onBackStack = { onBackPopUp.invoke() }
        )

        BackHandler {
            onBackPopUp.invoke()
        }
        if (!isNewProduct && !isEditing) {
            ProductViewerScreen(
                uiState = uiState,
                onEditClick = {
                    viewModel.enterEditMode()
                },
                onDeleteClick = {
                    selectedProductToDelete = it
                },
                onDeletePopUp = { onDeletePopUp.invoke() }
            )
        } else {
            ProductFormScreen(
                uiState = uiState,
                isNew = isNewProduct,
                formState = formState,
                onFieldChange = { viewModel.onFieldChange(it) },
                onSaveClick = {
                    selectedProductToSave = it
                },
                onDeleteClick = {
                    selectedProductToDelete = it
                },
                onUnitMeasureClick = {
                    viewModel.loadUnitMeasure()
                    selectedUnitMeasureToClick = true
                },
                onCategoryClick = {
                    viewModel.loadCategory()
                    selectedCategoryToClick = true
                },
                onBrandClick = {
                    viewModel.loadBrand()
                    selectedBrandToClick = true
                },
                onStorageClick = {
                    viewModel.loadStorageType()
                    selectedStorageToClick = true
                },
                onClearUnitMeasure = {
                    selectedUnitMeasure = null
                },
                onClearCategory = {
                    selectedCategory = null
                },
                onClearBrand = {
                    selectedBrand = null
                },
                onClearStorage = {
                    selectedStorage = null
                },
                selectedUnitMeasure = selectedUnitMeasure,
                selectedCategory = selectedCategory,
                selectedBrand = selectedBrand,
                selectedStorage = selectedStorage,
                onBackPopUp = onBackPopUp,
                onDeletePopUp = onDeletePopUp
            )
        }

        if (selectedBrandToClick) {
            CustomBottomSheet(
                isVisible = true,
                onDismiss = { selectedBrandToClick = false }) {
                SearchContent(
                    items = brandList,
                    keySelector = { it.id },
                    valueSelector = { it.name },
                    query = queryBrand,
                    onQueryChange = {
                        queryBrand = it
                        viewModel.onSearchBrand(it)
                    },
                    onSelectItem = {
                        queryBrand = ""
                        selectedBrand = it
                        selectedBrandToClick = false
                    }
                )
            }
        }

        if (selectedStorageToClick) {
            CustomBottomSheet(
                isVisible = true,
                onDismiss = { selectedStorageToClick = false }) {
                SearchContent(
                    items = storageList,
                    keySelector = { it.id },
                    valueSelector = { it.nameFull },
                    query = queryStorage,
                    onQueryChange = {
                        queryStorage = it
                        viewModel.onSearchStorage(it)
                    },
                    onSelectItem = {
                        queryStorage = ""
                        selectedStorage = it
                        selectedStorageToClick = false
                    }
                )
            }
        }

        if (selectedCategoryToClick) {
            CustomBottomSheet(
                isVisible = true,
                onDismiss = { selectedCategoryToClick = false }) {
                SearchContent(
                    items = categoryList,
                    keySelector = { it.id },
                    valueSelector = { it.name },
                    query = queryCategory,
                    onQueryChange = {
                        queryCategory = it
                        viewModel.onSearchCategory(it)
                    },
                    onSelectItem = {
                        queryCategory = ""
                        selectedCategory = it
                        selectedCategoryToClick = false
                    }
                )
            }
        }

        if (selectedUnitMeasureToClick) {
            CustomBottomSheet(
                isVisible = true,
                onDismiss = { selectedUnitMeasureToClick = false }) {
                SearchContent(
                    items = unitMeasureList,
                    keySelector = { it.id },
                    valueSelector = { it.name },
                    query = queryUnitMeasure,
                    onQueryChange = {
                        queryUnitMeasure = it
                        viewModel.onSearchUnitMeasure(it)
                    },
                    onSelectItem = {
                        queryUnitMeasure = ""
                        selectedUnitMeasure = it
                        selectedUnitMeasureToClick = false
                    }
                )
            }
        }

        selectedProductToSave?.let {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(Res.string.tx_global_confirm_save_subtitle),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_save),
                onPrimaryClick = {
                    viewModel.onUpsertLocal(it)
                    selectedProductToSave = null
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = { selectedProductToSave = null },
                onDismiss = {
                    selectedProductToSave = null
                }
            )
        }

        selectedProductToDelete?.let {
            ConfirmDialog(
                title = stringResource(Res.string.tx_global_confirmation),
                description = stringResource(
                    Res.string.tx_global_confirm_delete_register_message
                ),
                primaryButtonText = stringResource(Res.string.tx_global_confirm_delete),
                onPrimaryClick = {
                    viewModel.onUpsertLocal(it.copy(isDeleted = true))
                    selectedProductToDelete = null
                },
                secondaryButtonText = stringResource(Res.string.tx_global_cancel),
                onSecondaryClick = { selectedProductToDelete = null },
                onDismiss = {
                    selectedProductToDelete = null
                }
            )
        }

    }
}