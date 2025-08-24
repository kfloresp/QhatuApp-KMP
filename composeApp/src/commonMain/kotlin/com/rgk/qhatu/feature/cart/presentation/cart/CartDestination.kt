package com.rgk.qhatu.feature.cart.presentation.cart

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PauseCircleFilled
import androidx.compose.material.icons.filled.PauseCircleOutline
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rgk.qhatu.navigation.ProvideAppBar
import kotlinx.serialization.Serializable

@Serializable
data object CartDestination

internal fun NavGraphBuilder.cartDestination(
) {
    composable<CartDestination> {
        ProvideAppBar(
            actions = {
                Row {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            Icons.Default.Pause,
                            contentDescription = "Pausar"
                        )
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(
                            Icons.Default.DeleteSweep,
                            contentDescription = "Limpiar carrito"
                        )
                    }
                }
            }
        )
        CartScreen()
    }
}