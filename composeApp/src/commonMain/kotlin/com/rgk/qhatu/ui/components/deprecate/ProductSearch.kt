package com.rgk.qhatu.ui.components.deprecate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.domain.feature.product.model.Product
import com.rgk.qhatu.ui.feature.search.SearchResultState
import com.rgk.qhatu.ui.feature.search.SearchType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSearch(
    uiState: SearchResultState,
    onSearch: (query: String, searchType: SearchType) -> Unit,
    onClearSearch: () -> Unit,
    onProductSelected: (Product) -> Unit
) {
    val searchQuery = remember { mutableStateOf("") }
    val selectedSearchType = remember { mutableStateOf<SearchType>(SearchType.Ean) }
    val searchTypes = listOf(SearchType.Ean, SearchType.Name, SearchType.Code)
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showAdvancedSearch by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    if (showBottomSheet && uiState is SearchResultState.MultipleResults) {
        ModalBottomSheet(
            onDismissRequest = {
                onClearSearch()
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Column {
                uiState.products.take(4).forEach { product ->
                    ListItem(
                        headlineContent = { Text(product.nombre) },
                        supportingContent = { Text("Código: ${product.id} - EAN: ${product.ean}") },
                        modifier = Modifier.clickable {
                            showBottomSheet = false
                            searchQuery.value = ""
                            onClearSearch()
                            onProductSelected(product)
                        }
                    )
                }
                if (uiState.products.size > 4) {
                    Text(
                        text = "Mostrar más...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showBottomSheet = false
                                showAdvancedSearch = true
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }

    if (showAdvancedSearch && uiState is SearchResultState.MultipleResults) {
        FullScreenDialog(onDismiss = {
            showAdvancedSearch = false
        }) {
            ProductSearchAdvanced(
                products = (uiState as? SearchResultState.MultipleResults)?.products.orEmpty(),
                onProductSelected = { product ->
                    showAdvancedSearch = false
                    searchQuery.value = ""
                    onClearSearch()
                    onProductSelected(product)
                },
                onBack = {
                    showAdvancedSearch = false
                }
            )
        }
    }

    SearchTypeSelector(
        items = searchTypes,
        selectedItem = selectedSearchType.value,
        onItemSelected = { selectedSearchType.value = it },
        labelSelector = { it.label }
    )

    Spacer(modifier = Modifier.height(8.dp))

    SearchInputBar(
        query = searchQuery.value,
        onQueryChange = { searchQuery.value = it },
        onSearch = {
            onSearch(searchQuery.value, selectedSearchType.value)
        },
        onScanClick = {  }
    )

    Spacer(modifier = Modifier.height(16.dp))

    LaunchedEffect(uiState,showAdvancedSearch) {
        if (
            uiState is SearchResultState.MultipleResults &&
            !showBottomSheet &&
            !showAdvancedSearch
        ) {
            showBottomSheet = true
        }
        if (uiState is SearchResultState.SingleResult && searchQuery.value.isNotBlank()) {
            onProductSelected(uiState.product)
            searchQuery.value = ""
            keyboardController?.hide()
        }
    }
}
