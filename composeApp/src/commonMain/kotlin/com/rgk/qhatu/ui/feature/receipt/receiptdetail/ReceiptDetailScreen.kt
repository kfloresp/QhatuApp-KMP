package com.rgk.qhatu.ui.feature.receipt.receiptdetail

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rgk.qhatu.ui.components.AppToolbar
import com.rgk.qhatu.ui.feature.home.HomeItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_back

@Preview
@Composable
fun ReceiptDetailScreen(
    navController: NavController,
    viewModel: ReceiptDetailViewModel = koinViewModel()
)
{
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .pointerInput(Unit) { detectTapGestures { keyboardController?.hide() } }
    ) {
        AppToolbar(
            title = stringResource(HomeItem.ReceiptDetail.title),
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = stringResource(Res.string.tx_back)
                    )
                }
            },
            backgroundColor = HomeItem.ReceiptDetail.color
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}