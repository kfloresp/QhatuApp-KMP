package com.rgk.qhatu.ui.feature.search.advancedsearch
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.ui.components.AppToolbar
import com.rgk.qhatu.ui.components.ErrorMessageView
import com.rgk.qhatu.ui.components.LoadingProgress

@Composable
fun AdvancedSearchScreen(
    viewModel: AdvancedSearchViewModel,
    navController: NavController
) {
    val uiState = viewModel.uiState.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppToolbar(
                title = "Búsqueda Avanzada",
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Volver"
                        )
                    }
                },
                backgroundColor = Color(0xFF4CAF50)
            )

            if (uiState is AdvancedSearchUiState.Success) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(uiState.products) { product ->
                        ListItem(
                            headlineContent = { Text(product.nombre) },
                            supportingContent = {
                                Text("Código: ${product.id} - EAN: ${product.ean}")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("selected_product_id", product.id)
                                    navController.popBackStack()
                                }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        if (uiState is AdvancedSearchUiState.Loading) {
            LoadingProgress()
        }

        if (uiState is AdvancedSearchUiState.Error) {
            ErrorMessageView(
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
