package com.rgk.qhatu.feature.sale.presentation.receipt.searchprovider

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.components.deprecate.AppToolbar
import com.rgk.qhatu.components.deprecate.ErrorView
import com.rgk.qhatu.components.deprecate.LoadingView
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_back

@Composable
fun SearchProviderScreen(
    viewModel: SearchProviderViewModel,
    navController: NavController
) {
    val uiState = viewModel.uiState.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppToolbar(
                title = "Search",
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = stringResource(Res.string.tx_back)
                        )
                    }
                }
            )

            if (uiState is SearchProviderUiState.Success) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(uiState.clients) { provider ->
                        ListItem(
                            headlineContent = { Text(provider.razonSocial.orEmpty()) },
                            supportingContent = {
                                Text("Código: ${provider.id}")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("provider_id", provider.razonSocial)
                                    navController.popBackStack()
                                }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        if (uiState is SearchProviderUiState.Loading) {
            LoadingView()
        }

        if (uiState is SearchProviderUiState.Error) {
            ErrorView(
                message = uiState.message,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.initSearch()
    }
}