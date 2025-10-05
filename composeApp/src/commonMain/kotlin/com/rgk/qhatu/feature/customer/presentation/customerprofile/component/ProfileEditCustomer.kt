package com.rgk.qhatu.feature.customer.presentation.customerprofile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.image.CircularIcon
import com.rgk.qhatu.common.theme.QhatuTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProfileEditCustomer(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(160.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularIcon(
            icon = Icons.Default.Person,
            size = 150.dp,
            iconSize = 48.dp
        )

        IconButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-8).dp, y = (-8).dp)
                .size(36.dp)
        ) {
            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primary,
                shadowElevation = 4.dp
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileEditCustomerReview() {
    QhatuTheme {
        Column(Modifier.background(Color.White)) {
            ProfileEditCustomer(onClick = {})
        }
    }
}