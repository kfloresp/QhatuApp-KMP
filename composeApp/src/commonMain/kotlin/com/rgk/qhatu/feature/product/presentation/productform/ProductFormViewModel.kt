package com.rgk.qhatu.feature.product.presentation.productform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.rgk.qhatu.common.model.SyncOperation
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.product.domain.model.ImageProduct
import com.rgk.qhatu.feature.product.domain.model.Product
import com.rgk.qhatu.feature.product.domain.usecase.GetAllProductsUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SaveImageProductUseCase
import com.rgk.qhatu.feature.product.domain.usecase.SyncProductUseCase
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.model.Category
import com.rgk.qhatu.feature.setting.domain.model.UnitMeasure
import com.rgk.qhatu.feature.setting.domain.usecase.GetBrandsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetCategoriesUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.GetUnitsMeasureUseCase
import com.rgk.qhatu.shared.SharedImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.plus

class ProductFormViewModel(
    private val getUnitMeasureUseCase: GetUnitsMeasureUseCase,
    private val getCategoryUseCase: GetCategoriesUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val syncProductUseCase: SyncProductUseCase,
    private val saveImageProductUseCase: SaveImageProductUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ProductFormUiState>(ProductFormUiState.Loading)
    val uiState: StateFlow<ProductFormUiState> = _uiState.asStateFlow()

    private val _formState = MutableStateFlow(ProductFormValidationState())
    val formState: StateFlow<ProductFormValidationState> = _formState.asStateFlow()
    private val _isNewProduct =
        MutableStateFlow(false)
    val isNewProduct: StateFlow<Boolean> = _isNewProduct.asStateFlow()

    private val destinationArgs = savedStateHandle.toRoute<ProductFormDestination>()
    val productId get():String = destinationArgs.productId

    private val _categoryList = MutableStateFlow<List<Category>>(emptyList())
    val categoryList: StateFlow<List<Category>> = _categoryList.asStateFlow()
    private val _brandList = MutableStateFlow<List<Brand>>(emptyList())
    val brandList: StateFlow<List<Brand>> = _brandList.asStateFlow()
    private val _unitMeasureList = MutableStateFlow<List<UnitMeasure>>(emptyList())
    val unitMeasureList: StateFlow<List<UnitMeasure>> = _unitMeasureList.asStateFlow()

    private var allItemsCategory: List<Category> = emptyList()
    private var allItemsUnitMeasure: List<UnitMeasure> = emptyList()
    private var allItemsBrand: List<Brand> = emptyList()
   // private var allItemsStorage: List<Configuration> = emptyList()

    private val _isEditing = MutableStateFlow(false)
    val isEditing: StateFlow<Boolean> = _isEditing

    fun enterEditMode() {
        _isEditing.value = true
    }

    fun exitEditMode() {
        _isEditing.value = false
    }

    init {
        if (productId.isEmpty()) {
            _isNewProduct.value = true
            _uiState.value = ProductFormUiState.Success(Product())
            _formState.update {
                ProductFormValidationState()
            }
        } else {
            loadProduct(productId)
        }
    }

    private fun loadProduct(productId: String) {
        viewModelScope.launch {
            _uiState.update {
                ProductFormUiState.Loading
            }
            val result = getAllProductsUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        ProductFormUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<List<Product>> -> {
                    val product = result.data.first()
                    _uiState.update {
                        ProductFormUiState.Success(
                            result = product
                        )
                    }
                    _formState.update {
                        ProductFormValidationState(product, false)
                    }
                }
            }
        }
    }

    fun loadUnitMeasure() {
        viewModelScope.launch {
            val result = getUnitMeasureUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _unitMeasureList.value = emptyList()
                }

                is SyncResult.Success<List<UnitMeasure>> -> {
                    _unitMeasureList.value = result.data
                    allItemsUnitMeasure = result.data
                }
            }
        }
    }

    fun loadCategory() {
        viewModelScope.launch {
            val result = getCategoryUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _categoryList.value = emptyList()
                }

                is SyncResult.Success<List<Category>> -> {
                    _categoryList.value = result.data
                    allItemsCategory = result.data
                }
            }
        }
    }

    fun loadBrand() {
        viewModelScope.launch {
            val result = getBrandsUseCase()
            when (result) {
                is SyncResult.Error -> {
                    _brandList.value = emptyList()
                }

                is SyncResult.Success<List<Brand>> -> {
                    _brandList.value = result.data
                    allItemsBrand = result.data
                }
            }
        }
    }

    fun onFieldChange(update: Product.() -> Product) {
        val currentFields = _formState.value.fields
        val updatedFields = currentFields.update()
        val isValid = validateFields(updatedFields)
        _formState.value = ProductFormValidationState(updatedFields, isValid)
    }

    private fun validateFields(fields: Product): Boolean {
        return fields.name.isNotBlank() &&
                fields.ean.isNotBlank() &&
                fields.categoryId.orEmpty().isNotBlank() &&
                fields.unitMeasureId.isNotBlank() &&
                fields.brandId.orEmpty().isNotBlank()
    }

    fun onSearchBrand(query: String) {
        val filtered = if (query.isBlank()) allItemsBrand
        else allItemsBrand.filter { it.name.contains(query, ignoreCase = true) }
        _brandList.value = filtered
    }

    fun onSearchCategory(query: String) {
        val filtered = if (query.isBlank()) allItemsCategory
        else allItemsCategory.filter { it.name.contains(query, ignoreCase = true) }
        _categoryList.value = filtered
    }

    fun onSearchUnitMeasure(query: String) {
        val filtered = if (query.isBlank()) allItemsUnitMeasure
        else allItemsUnitMeasure.filter { it.name.contains(query, ignoreCase = true) }
        _unitMeasureList.value = filtered
    }

    fun onUpsertLocal(product: Product) {
        viewModelScope.launch {
            _uiState.update {
                ProductFormUiState.Loading
            }
            val result = syncProductUseCase(SyncOperation.UpsertLocal(product))
            when (result) {
                is SyncResult.Error -> {
                    _uiState.update {
                        ProductFormUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    _uiState.update {
                        ProductFormUiState.SuccessUpsert
                    }
                }
            }
        }
    }

    fun loadStorageType() {
//        viewModelScope.launch {
//            val result = getStorageTypeUseCase()
//            when (result) {
//                is SyncResult.Error -> {
//                    _storageList.value = emptyList()
//                }
//
//                is SyncResult.Success<List<Configuration>> -> {
//                    _storageList.value = result.data
//                    allItemsStorage = result.data
//                }
//            }
//        }
    }

    fun onSearchStorage(query: String) {
//        val filtered = if (query.isBlank()) allItemsStorage
//        else allItemsStorage.filter { it.name.contains(query, ignoreCase = true) }
//        _storageList.value = filtered
    }

    fun newImageCaptured(result: SharedImage) {
        viewModelScope.launch(Dispatchers.IO) {
            val tempImage = ImageProduct(
                isLoading = true,
            )
            onFieldChange {
                copy(
                    imageProduct = imageProduct + tempImage
                )
            }
            val result = saveImageProductUseCase(result)
            when (result) {
                is SyncResult.Error -> {
                    ProductFormUiState.Error(result.exception.message.orEmpty())
                }

                is SyncResult.Success<String> -> {
                    val path = result.data
                    if (path.isNotBlank()) {
                        onFieldChange {
                            copy(
                                imageProduct = imageProduct - tempImage + ImageProduct(
                                    filename = path,
                                    isTemp = true,
                                )
                            )
                        }
                    }
                }
            }
        }
    }

}