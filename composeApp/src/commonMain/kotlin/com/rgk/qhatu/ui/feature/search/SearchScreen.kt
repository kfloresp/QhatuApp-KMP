package com.rgk.qhatu.ui.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.ui.components.AppToolbar
import com.rgk.qhatu.ui.components.ErrorMessageView
import com.rgk.qhatu.ui.components.LoadingProgress
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = koinViewModel()) {
    val searchQuery = remember { mutableStateOf("") }
    val selectedSearchType = remember { mutableStateOf<SearchType>(SearchType.Ean) }
    val focusManager = LocalFocusManager.current
    val searchTypes = listOf(SearchType.Ean, SearchType.Name, SearchType.Code)
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState
    val selectedProductId = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<String>("selected_product_id")

    if (showBottomSheet && uiState is SearchResultState.MultipleResults) {
        val resultState = uiState as SearchResultState.MultipleResults
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.clearSearchResult()
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Column {
                resultState.products.take(4).forEach { product ->
                    ListItem(
                        headlineContent = { Text(product.nombre) },
                        supportingContent = { Text("Código: ${product.id} - EAN: ${product.ean}") },
                        modifier = Modifier.clickable {
                            showBottomSheet = false
                            searchQuery.value = ""
                            viewModel.clearSearchResult()
                            viewModel.searchProducts(product.ean, SearchType.Ean.code)
                        }
                    )
                }
                if (resultState.products.size > 4) {
                    Text(
                        text = "Mostrar más...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showBottomSheet = false
                                navController.navigate("advanced-search/${searchQuery.value}/${selectedSearchType.value.code}")
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AppToolbar(
            title = "Búsqueda de producto", navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Volver"
                    )
                }
            },
            backgroundColor = Color(0xFF4CAF50)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            searchTypes.forEach { type ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        selectedSearchType.value = type
                    }
                ) {
                    RadioButton(
                        selected = selectedSearchType.value::class == type::class,
                        onClick = {
                            selectedSearchType.value = type
                        }
                    )
                    Text(
                        text = type.label,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Ingresar código, nombre o EAN...") },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    viewModel.searchProducts(searchQuery.value, selectedSearchType.value.code)
                })
            )
            IconButton(onClick = {
                focusManager.clearFocus()
                //onScanClick()
            }) {
                Icon(Icons.Default.QrCodeScanner, contentDescription = "Escanear QR")
            }
            IconButton(onClick = {
                focusManager.clearFocus()
                viewModel.searchProducts(searchQuery.value, selectedSearchType.value.code)
            }) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState is SearchResultState.SingleResult) {
            ProductSearchItem((uiState as SearchResultState.SingleResult).product)
        }
        if (uiState is SearchResultState.Error) {
            ErrorMessageView(
                message = (uiState as SearchResultState.Error).message,
                modifier = Modifier.fillMaxSize()
            )
        }


    }

    if (uiState is SearchResultState.Loading) {
        LoadingProgress()
    }

    LaunchedEffect(uiState) {
        if (uiState is SearchResultState.MultipleResults) {
            showBottomSheet = true
        }
    }
    LaunchedEffect(searchQuery.value) {
        if (searchQuery.value.isNotBlank() &&
            viewModel.uiState.value !is SearchResultState.Idle &&
            viewModel.uiState.value !is SearchResultState.Loading
        ) {
            viewModel.clearSearchResult()
        }
    }

    LaunchedEffect(selectedProductId) {
        selectedProductId?.let {
            viewModel.searchProducts(it, SearchType.Code.code)
        }
    }

}