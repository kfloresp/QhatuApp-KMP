package com.rgk.qhatu.ui.feature.search

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.ui.components.AppToolbar
import com.rgk.qhatu.ui.components.ErrorView
import com.rgk.qhatu.ui.components.LoadingView
import com.rgk.qhatu.ui.components.ProductSearch
import com.rgk.qhatu.ui.components.ProductSearchItem
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_back

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = koinViewModel()) {
    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier =
            Modifier.fillMaxSize()
                .verticalScroll(scrollState)
                .pointerInput(Unit) { detectTapGestures { keyboardController?.hide() } },
    ) {
        AppToolbar(
            title = "Search", navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = stringResource(Res.string.tx_back)
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        ProductSearch(
            uiState = uiState,
            onSearch = { q, t -> viewModel.searchProducts(q, t.code) },
            onClearSearch = { viewModel.clearSearchResult() },
            onProductSelected = { product -> viewModel.setSelectedProduct(product) }
        )
        if (uiState is SearchResultState.SingleResult) {
            val product = (uiState as SearchResultState.SingleResult).product
            ProductSearchItem(product)
        }
    }

    when (uiState) {
        is SearchResultState.Error -> {
            ErrorView(
                message = (uiState as SearchResultState.Error).message,
                modifier = Modifier.fillMaxSize()
            )
        }

        is SearchResultState.Loading -> {
            LoadingView(
                modifier = Modifier.fillMaxSize()
            )
        }

        else -> Unit
    }

}