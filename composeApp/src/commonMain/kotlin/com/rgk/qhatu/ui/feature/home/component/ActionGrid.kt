package com.rgk.qhatu.ui.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.rgk.qhatu.utils.QhatuTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.title_customers
import qhatuapp.composeapp.generated.resources.title_payments
import qhatuapp.composeapp.generated.resources.title_products
import qhatuapp.composeapp.generated.resources.title_sales
import qhatuapp.composeapp.generated.resources.title_search
import qhatuapp.composeapp.generated.resources.title_setting

data class Action(val text: StringResource, val icon: ImageVector, val navigateTo: () -> Unit)

@Composable
fun ActionGrid(actions: List<Action>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(actions) { action ->
            ActionItemCard(
                text = stringResource(action.text),
                icon = action.icon,
                onClick = action.navigateTo
            )
        }
    }
}

fun provideMenu(
    onSearchClick: () -> Unit,
    onSalesClick: () -> Unit,
    onPaymentsClick: () -> Unit,
    onClientsClick: () -> Unit,
    onProductsClick: () -> Unit,
    onSettingsClick: () -> Unit
): List<Action> = listOf(
    Action(Res.string.title_search, Icons.Default.Search, onSearchClick),
    Action(Res.string.title_sales, Icons.AutoMirrored.Filled.ReceiptLong, onSalesClick),
    Action(Res.string.title_payments, Icons.Default.CreditCard, onPaymentsClick),
    Action(Res.string.title_customers, Icons.Default.Group, onClientsClick),
    Action(Res.string.title_products, Icons.Default.Inventory2, onProductsClick),
    Action(Res.string.title_setting, Icons.Default.Settings, onSettingsClick)
)

@Preview()
@Composable
fun ActionGridPreview() {
    QhatuTheme {
        ActionGrid(provideMenu({}, {}, {}, {}, {}, {}))
    }
}