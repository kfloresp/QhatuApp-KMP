package com.rgk.qhatu.common.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonActions(
    modifier: Modifier = Modifier,
    primaryButtonText: String,
    onPrimaryClick: () -> Unit,
    isEnabled: Boolean = false,
    isColumn: Boolean = false,
    secondaryButtonText: String? = null,
    onSecondaryClick: (() -> Unit)? = null,
){
    if (isColumn){
        Column (
            modifier = modifier.fillMaxWidth().wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (secondaryButtonText != null && onSecondaryClick != null) {
                OutlinedButton(
                    onClick = onSecondaryClick
                ) {
                    Text(secondaryButtonText)
                }
            }
            Button(onClick = onPrimaryClick, enabled = isEnabled) {
                Text(primaryButtonText)
            }
        }
    }else{
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.CenterHorizontally
            )
        ) {
            if (secondaryButtonText != null && onSecondaryClick != null) {
                OutlinedButton(
                    onClick = onSecondaryClick, modifier = Modifier.weight(1f),
                ) {
                    Text(secondaryButtonText)
                }
            }
            Button(onClick = onPrimaryClick, modifier = Modifier.weight(1f), enabled = isEnabled) {
                Text(primaryButtonText)
            }
        }
    }
}