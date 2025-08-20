package com.rgk.qhatu.feature.home.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.rgk.qhatu.common.theme.QhatuTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.title_customers
import qhatuapp.composeapp.generated.resources.title_payments
import qhatuapp.composeapp.generated.resources.title_products
import qhatuapp.composeapp.generated.resources.title_sales
import qhatuapp.composeapp.generated.resources.title_search
import qhatuapp.composeapp.generated.resources.title_setting

data class Action(val text: StringResource, val icon: ImageVector, val navigateTo: () -> Unit)
data class ActionSection(
    val title: String,
    val actions: List<Action>
)

fun provideMenu(
    onSearchClick: () -> Unit,
    onSalesClick: () -> Unit,
    onPaymentsClick: () -> Unit,
    onCustomersClick: () -> Unit,
    onProductsClick: () -> Unit,
    onSettingsClick: () -> Unit
): List<ActionSection> = listOf(
    ActionSection(
        title = "Acciones rápidas",
        actions = listOf(
            Action(Res.string.title_search, Icons.Default.Search, onSearchClick)
        )
    ),
    ActionSection(
        title = "Operaciones",
        actions = listOf(
            Action(Res.string.title_sales, Icons.AutoMirrored.Filled.ReceiptLong, onSalesClick),
            Action(Res.string.title_payments, Icons.Default.CreditCard, onPaymentsClick)
        )
    ),
    ActionSection(
        title = "Gestión",
        actions = listOf(
            Action(Res.string.title_customers, Icons.Default.Group, onCustomersClick),
            Action(Res.string.title_products, Icons.Default.Inventory2, onProductsClick)
        )
    ),
    ActionSection(
        title = "Preferencias",
        actions = listOf(
            Action(Res.string.title_setting, Icons.Default.Settings, onSettingsClick)
        )
    )
)

@Preview()
@Composable
fun ActionGridPreview() {
    QhatuTheme {
        ActionSection(
            title = "Preferencias",
            actions = listOf(
                Action(Res.string.title_setting, Icons.Default.Settings) {}
            )
        )
    }
}