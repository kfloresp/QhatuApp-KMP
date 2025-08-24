package com.rgk.qhatu.feature.sale.presentation.receipt.searchprovider

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.common.components.loading.LoadingOverlay

@Composable
fun SearchProviderScreen(
    viewModel: SearchProviderViewModel,
    navController: NavController
) {
    val uiState = viewModel.uiState.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            if (uiState is SearchProviderUiState.Success) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(uiState.customers) { provider ->
                        ListItem(
                            headlineContent = { Text(provider.id.orEmpty()) },
                            supportingContent = {
                                Text("Código: ${provider.id}")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("provider_id", provider.id)
                                    navController.popBackStack()
                                }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        if (uiState is SearchProviderUiState.Loading) {
            LoadingOverlay()
        }

        if (uiState is SearchProviderUiState.Error) {

        }
    }

    LaunchedEffect(Unit) {
        viewModel.initSearch()
    }
}