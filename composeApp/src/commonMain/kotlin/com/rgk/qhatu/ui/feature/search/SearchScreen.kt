package com.rgk.qhatu.ui.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.ui.components.AppToolbar
import com.rgk.qhatu.ui.components.ErrorView
import com.rgk.qhatu.ui.components.LoadingView
import com.rgk.qhatu.ui.components.SearchInputBar
import com.rgk.qhatu.ui.components.SearchTypeSelector
import com.rgk.qhatu.ui.feature.home.HomeItem
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_back

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = koinViewModel()) {
    val searchQuery = remember { mutableStateOf("") }
    val selectedSearchType = remember { mutableStateOf<SearchType>(SearchType.Ean) }
    val searchTypes = listOf(SearchType.Ean, SearchType.Name, SearchType.Code)
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
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

    Column(
        modifier =
            Modifier.fillMaxSize()
                .verticalScroll(scrollState)
                .pointerInput(Unit) { detectTapGestures { keyboardController?.hide() } },
    ) {
        AppToolbar(
            title = stringResource(HomeItem.Search.title), navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = stringResource(Res.string.tx_back)
                    )
                }
            },
            backgroundColor = HomeItem.Search.color
        )
        Spacer(modifier = Modifier.height(8.dp))

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
                viewModel.searchProducts(searchQuery.value, selectedSearchType.value.code)
            },
            onScanClick = {

            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState is SearchResultState.SingleResult) {
            ProductSearchItem((uiState as SearchResultState.SingleResult).product)
        }
        if (uiState is SearchResultState.Error) {
            ErrorView(
                message = (uiState as SearchResultState.Error).message,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    if (uiState is SearchResultState.Loading) {
        LoadingView()
    }

    LaunchedEffect(uiState) {
        if (uiState is SearchResultState.MultipleResults) {
            showBottomSheet = true
        }
        if (uiState is SearchResultState.SingleResult) {
            searchQuery.value = ""
            keyboardController?.hide()
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