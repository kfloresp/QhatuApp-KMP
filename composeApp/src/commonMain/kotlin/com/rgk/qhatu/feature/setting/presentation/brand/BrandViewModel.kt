package com.rgk.qhatu.feature.setting.presentation.brand

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.common.model.SyncResult
import com.rgk.qhatu.feature.setting.domain.model.Brand
import com.rgk.qhatu.feature.setting.domain.usecase.GetBrandsUseCase
import com.rgk.qhatu.feature.setting.domain.usecase.UpsertBrandUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val DELAY_TIME = 500L
class BrandViewModel(
    private val upsertBrandUseCase: UpsertBrandUseCase,
    private val getBrandsUseCase: GetBrandsUseCase
) : ViewModel() {
    private var allItems: List<Brand> = emptyList()

    private val _listUiState = MutableStateFlow<BrandUiState>(BrandUiState.Loading)
    val listUiState: StateFlow<BrandUiState> = _listUiState.asStateFlow()

    private val _formUiState = MutableStateFlow<BrandFormUiState>(BrandFormUiState.Idle)
    val formUiState: StateFlow<BrandFormUiState> = _formUiState.asStateFlow()

    init {
        fetchLocal()
    }

    private fun fetchLocal() {
        viewModelScope.launch {
            _listUiState.update {
                BrandUiState.Loading
            }
            delay(DELAY_TIME)
            when (val result = getBrandsUseCase()) {
                is SyncResult.Error -> {
                    _listUiState.update {
                        BrandUiState.Error(result.exception.message.orEmpty())
                    }
                }

                is SyncResult.Success<*> -> {
                    allItems = result.data as List<Brand>
                    if (allItems.isNotEmpty()) {
                        _listUiState.update {
                            BrandUiState.Success(
                                result = allItems
                            )
                        }
                    } else {
                        _listUiState.update {
                            BrandUiState.Empty
                        }
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        val current = _listUiState.value as? BrandUiState.Success ?: return
        val filtered = if (query.isBlank()) allItems
        else allItems.filter { it.name.contains(query, ignoreCase = true) }
        _listUiState.value = current.copy(result = filtered, query = query)
    }

    fun startUpsert(brand: Brand? = null) {
        val base = brand ?: Brand()
        _formUiState.value = BrandFormUiState.Upsert(base, validateFields(base))
    }

    fun onFieldChange(update: Brand.() -> Brand) {
        val current = _formUiState.value as? BrandFormUiState.Upsert ?: return
        val updated = current.brand.update()
        _formUiState.value = BrandFormUiState.Upsert(updated, validateFields(updated))
    }

    fun onUpsertBrand(brand: Brand) {
        val current = _formUiState.value as? BrandFormUiState.Upsert ?: return
        _formUiState.value = current.copy(isLoading = true)
        viewModelScope.launch {
            delay(DELAY_TIME)
            when (val result = upsertBrandUseCase(brand)) {
                is SyncResult.Error -> {
                    _formUiState.value =
                        BrandFormUiState.Error(result.exception.message.orEmpty())
                }

                is SyncResult.Success<*> -> {
                    _formUiState.value = BrandFormUiState.Idle
                    fetchLocal()
                }
            }
        }
    }

    fun cancelForm() {
        _formUiState.value = BrandFormUiState.Idle
    }

    private fun validateFields(brand: Brand): Boolean {
        return brand.name.isNotBlank()
    }

}
