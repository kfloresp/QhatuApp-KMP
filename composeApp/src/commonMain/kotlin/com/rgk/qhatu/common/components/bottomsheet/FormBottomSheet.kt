package com.rgk.qhatu.common.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActionsRow
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_save_changes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBottomSheet(
    isVisible: Boolean,
    config: FormConfig,
    isValidForm: Boolean,
    isLoading: Boolean = false,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { newValue ->
            !(isLoading && newValue == SheetValue.Hidden)
        }
    )

    LaunchedEffect(isVisible) {
        if (isVisible) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        scrimColor = Color.Black.copy(alpha = 0.32f),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp,
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(config.title, fontWeight = FontWeight.SemiBold)

                content()

                if (isLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                }

                ButtonActionsRow(
                    primaryButtonText = stringResource(Res.string.tx_global_save_changes),
                    onPrimaryClick = onConfirm,
                    isEnabled = !isLoading && isValidForm,
                    secondaryButtonText = config.secondaryText,
                    onSecondaryClick = config.secondaryAction
                )
            }
        }
    )

}

data class FormConfig(
    val title: String,
    val secondaryText: String,
    val secondaryAction: () -> Unit,
)