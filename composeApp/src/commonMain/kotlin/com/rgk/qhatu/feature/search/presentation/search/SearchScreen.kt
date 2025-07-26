package com.rgk.qhatu.feature.search.presentation.search

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.common.components.loading.LoadingView
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    uiState: SearchResultState,
    navigateToCart: () -> Unit,
    openScanQR: () -> Unit
) {
    Box(Modifier.fillMaxSize()){
        Text("Search Screen")
    }
}

@Composable
fun SearchScreen_2(navController: NavController, viewModel: SearchViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier =
            Modifier.fillMaxSize()
                .verticalScroll(scrollState)
                .pointerInput(Unit) { detectTapGestures { keyboardController?.hide() } },
    ) {

        Spacer(modifier = Modifier.height(8.dp))
        if (uiState is SearchResultState.SingleResult) {
            val product = (uiState as SearchResultState.SingleResult).product
        }
    }

    when (uiState) {
        is SearchResultState.Error -> {


        }

        is SearchResultState.Loading -> {
            LoadingView(
                modifier = Modifier.fillMaxSize()
            )
        }

        else -> Unit
    }

}