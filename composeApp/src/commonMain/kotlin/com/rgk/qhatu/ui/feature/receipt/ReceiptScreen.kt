package com.rgk.qhatu.ui.feature.receipt

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.ui.components.AppToolbar
import com.rgk.qhatu.ui.components.ErrorView
import com.rgk.qhatu.ui.components.LoadingView
import com.rgk.qhatu.ui.components.PrimaryButton
import com.rgk.qhatu.ui.components.SimpleDatePicker
import com.rgk.qhatu.ui.components.toFormat
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_back
import qhatuapp.composeapp.generated.resources.tx_date
import qhatuapp.composeapp.generated.resources.tx_provider
import qhatuapp.composeapp.generated.resources.tx_provider_only
import qhatuapp.composeapp.generated.resources.tx_selected_date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptScreen(
    navController: NavController,
    viewModel: ReceiptViewModel = koinViewModel()
) {
    val searchQuery = remember { mutableStateOf("") }
    val uiState by viewModel.uiState
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var showPicker by remember { mutableStateOf(false) }
    val selectedProviderId = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<String>("SELECTED_PROVIDER")

    if (showBottomSheet && uiState is ReceiptState.Multiple) {
        val result = uiState as ReceiptState.Multiple
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                viewModel.clearSearch()
            },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                result.providers.take(4).forEach { provider ->
                    ListItem(
                        headlineContent = { Text(provider.razonSocial.orEmpty()) },
                        supportingContent = { Text("${stringResource(Res.string.tx_provider)} ${provider.id}") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showBottomSheet = false
                                searchQuery.value = ""
                                viewModel.clearSearch()
                                viewModel.searchProvider(provider.razonSocial.orEmpty())
                            }
                    )

                }
                if (result.providers.size > 4){
                    Text(
                        text = "Mostrar más...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showBottomSheet = false
                                //navController.navigate(RouteNavigation.SearchProvider.createRoute(searchQuery.value))
                            }
                            .padding(16.dp)
                    )
                }

            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .pointerInput(Unit) { detectTapGestures { keyboardController?.hide() } }
    ) {
        AppToolbar(
            title = "Recibo",
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = stringResource(Res.string.tx_back)
                    )
                }
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = selectedDate.toFormat(),
            onValueChange = {},
            label = { Text(stringResource(Res.string.tx_date)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = stringResource(Res.string.tx_selected_date),
                    modifier = Modifier.clickable {
                        showPicker = true
                    }
                )
            }
        )

        if (showPicker) {
            SimpleDatePicker(
                showPicker = showPicker,
                initialDate = selectedDate,
                onDateSelected = { date ->
                    selectedDate = date
                    showPicker = false
                },
                onDismiss = {
                    showPicker = false
                }
            )
        }

        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text(stringResource(Res.string.tx_provider_only)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                viewModel.searchProvider(searchQuery.value)
            }),
            trailingIcon = {
                IconButton(onClick = {
                    keyboardController?.hide()
                    viewModel.searchProvider(searchQuery.value)
                }) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState is ReceiptState.Single) {
            val provider = (uiState as ReceiptState.Single).provider
            ReceiptProviderItem(provider)
            PrimaryButton("Continuar",
                onClick = {},
                modifier = Modifier
                    .padding(20.dp))
        }

        if (uiState is ReceiptState.Error) {
            ErrorView(
                message = (uiState as ReceiptState.Error).message,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    if (uiState is ReceiptState.Loading) {
        LoadingView()
    }
    LaunchedEffect(uiState) {
        if (uiState is ReceiptState.Single) {
            searchQuery.value = ""
            keyboardController?.hide()
        }
        if (uiState is ReceiptState.Multiple) {
            showBottomSheet = true
        }
    }

    LaunchedEffect(searchQuery.value) {
        if (searchQuery.value.isNotBlank() &&
            viewModel.uiState.value !is ReceiptState.Idle &&
            viewModel.uiState.value !is ReceiptState.Loading
        ) {
            viewModel.clearSearch()
        }
    }

    LaunchedEffect(selectedProviderId) {
        selectedProviderId?.let {
            viewModel.searchProvider(it)
        }
    }
}