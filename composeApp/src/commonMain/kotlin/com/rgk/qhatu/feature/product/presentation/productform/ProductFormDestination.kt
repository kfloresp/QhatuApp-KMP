package com.rgk.qhatu.feature.product.presentation.productform

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.common.components.bottomsheet.CustomBottomSheet
import com.rgk.qhatu.common.components.dialog.ConfirmDialog
import com.rgk.qhatu.common.components.dialog.ContentDialog
import com.rgk.qhatu.common.components.search.SearchContent
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.Configuration
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.navigation.ProvideAppBar
import com.rgk.qhatu.shared.PermissionCallback
import com.rgk.qhatu.shared.PermissionStatus
import com.rgk.qhatu.shared.PermissionType
import com.rgk.qhatu.shared.SharedImageStorage
import com.rgk.qhatu.shared.createPermissionsManager
import com.rgk.qhatu.shared.rememberCameraManager
import com.rgk.qhatu.shared.rememberGalleryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        val coroutineScope = rememberCoroutineScope()
        var imageBitmapList by remember { mutableStateOf<List<ImageBitmap>>(emptyList()) }

        var imageSourceOptionDialog by remember { mutableStateOf(value = false) }
        var launchCamera by remember { mutableStateOf(value = false) }
        var launchGallery by remember { mutableStateOf(value = false) }
        var launchSetting by remember { mutableStateOf(value = false) }
        var permissionRationalDialog by remember { mutableStateOf(value = false) }
        val permissionsManager = createPermissionsManager(object : PermissionCallback {
            override fun onPermissionStatus(
                permissionType: PermissionType,
                status: PermissionStatus,
            ) {
                when (status) {
                    PermissionStatus.GRANTED -> {
                        when (permissionType) {
                            PermissionType.CAMERA -> launchCamera = true
                            PermissionType.GALLERY -> launchGallery = true
                        }
                    }

                    else -> {
                        permissionRationalDialog = true
                    }
                }
            }
        })
        val cameraManager = rememberCameraManager {
            coroutineScope.launch {
                val bitmap = withContext(Dispatchers.Default) {
                    it?.toImageBitmap()
                }
                if (bitmap != null) {
                    imageBitmapList = imageBitmapList + bitmap
                }
            }
        }

        val galleryManager = rememberGalleryManager {
            coroutineScope.launch {
                val bitmap = withContext(Dispatchers.Default) {
                    it?.toImageBitmap()
                }
                if (bitmap != null) {
                    imageBitmapList = imageBitmapList + bitmap
                }
            }
        }
        if (imageSourceOptionDialog) {
            ContentDialog("Añadir fotos", onDismiss = { imageSourceOptionDialog = false }) {
                Text("Elige una opción")
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Button(onClick = {
                        imageSourceOptionDialog = false
                        launchGallery = true
                    }) {
                        Text(text = "Galeria")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        imageSourceOptionDialog = false
                        launchCamera = true
                    }) {
                        Text(text = "Cámara")
                    }
                }
            }
        }
        if (launchGallery) {
            if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
                galleryManager.launch()
            } else {
                permissionsManager.askPermission(PermissionType.GALLERY)
            }
            launchGallery = false
        }
        if (launchCamera) {
            if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
                cameraManager.launch()
            } else {
                permissionsManager.askPermission(PermissionType.CAMERA)
            }
            launchCamera = false
        }
        if (launchSetting) {
            permissionsManager.launchSettings()
            launchSetting = false
        }
        if (permissionRationalDialog) {
            ConfirmDialog(
                title = "Permission Required",
                description = "To set your profile picture, please grant this permission. You can manage permissions in your device settings.",
                primaryButtonText = "Settings",
                secondaryButtonText = "Cancel",
                onPrimaryClick = {
                    permissionRationalDialog = false
                    launchSetting = true

                },
                onSecondaryClick = {
                    permissionRationalDialog = false
                })

        }

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
                onDeletePopUp = { onBackPopUp.invoke() }
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
                onImagePickerClick = {
                    imageSourceOptionDialog = true
                },
                imageBitmapList = imageBitmapList,
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
                    viewModel.onUpsertLocal(it,imageBitmapList)
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
                    viewModel.onUpsertLocal(it.copy(isDeleted = true), imageBitmapList)
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