package com.rgk.qhatu.feature.customer.presentation.customerprofile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.common.components.button.ButtonActions
import com.rgk.qhatu.common.components.button.PrimaryButton
import com.rgk.qhatu.common.components.button.SecondaryButton
import com.rgk.qhatu.common.components.image.CircularIcon
import com.rgk.qhatu.feature.customer.domain.model.Customer
import com.rgk.qhatu.feature.setting.presentation.setting.component.SettingItem
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_global_delete_changes
import qhatuapp.composeapp.generated.resources.tx_global_save_changes
import qhatuapp.composeapp.generated.resources.tx_profile_customer_address
import qhatuapp.composeapp.generated.resources.tx_profile_customer_edit
import qhatuapp.composeapp.generated.resources.tx_profile_customer_pending_balance
import qhatuapp.composeapp.generated.resources.tx_profile_customer_phone
import qhatuapp.composeapp.generated.resources.tx_profile_customer_view_debt_summary

@Composable
fun ProfileCustomer(customer: Customer, onEditClick: () -> Unit, onResumeClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(top = 40.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularIcon(icon = Icons.Default.Person, size = 150.dp, iconSize = 48.dp)
            Spacer(Modifier.height(10.dp))
            Text(
                customer.nameCustomer,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                customer.documentNumber, style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.outline,
            )
            Spacer(Modifier.height(10.dp))
            if (!customer.address.isNullOrEmpty()) {
                SettingItem(
                    icon = Icons.Outlined.LocationOn,
                    title = stringResource(Res.string.tx_profile_customer_address),
                    subtitle = customer.address,
                )
            }
            if (!customer.phoneNumber.isNullOrEmpty()) {
                SettingItem(
                    icon = Icons.Outlined.Call,
                    title = stringResource(Res.string.tx_profile_customer_phone),
                    subtitle = customer.phoneNumber,
                )
            }
            if (customer.havePendingCustomer) {
                SettingItem(
                    icon = Icons.Outlined.Payments,
                    title = stringResource(Res.string.tx_profile_customer_pending_balance),
                    subtitle = customer.pendingCustomer,
                )
            }
        }
        if (customer.havePendingCustomer) {
            ButtonActions(
                modifier = Modifier.padding(12.dp),
                primaryButtonText = stringResource(Res.string.tx_profile_customer_edit),
                isEnabled = true,
                isColumn = true,
                onPrimaryClick = {
                    onEditClick()
                },
                secondaryButtonText = stringResource(Res.string.tx_profile_customer_view_debt_summary),
                onSecondaryClick = { onResumeClick() }
            )
        }else{
            ButtonActions(
                modifier = Modifier.padding(12.dp),
                primaryButtonText = stringResource(Res.string.tx_profile_customer_edit),
                isEnabled = true,
                isColumn = true,
                onPrimaryClick = {
                    onEditClick()
                },
            )
        }
    }
}
