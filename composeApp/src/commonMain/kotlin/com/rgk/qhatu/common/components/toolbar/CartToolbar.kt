package com.rgk.qhatu.common.components.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rgk.qhatu.common.components.cart.CartIconWithBadge

@Composable
fun QhatuCartToolbar(
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    onBackClick: () -> Unit,
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = elevation,
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.width(48.dp)) {
                IconButton(
                    onClick = {
                        onBackClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
            Box(contentAlignment = Alignment.CenterStart) {
                Text(
                    title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.padding(end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                actions()
            }
        }
    }
}

@Composable
fun RightCartToolbar(
    shoppingCartPrice: String,
    shoppingCartQuantity: Int,
    onShoppingCartClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                onClick = onShoppingCartClick,
                role = Role.Button,
                indication = ripple(bounded = false),
                interactionSource = remember { MutableInteractionSource() },
            )
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = shoppingCartPrice,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.width(8.dp))
        VerticalDivider(
            Modifier
                .height(24.dp)
                .width(1.dp), thickness = 1.dp
        )
        Spacer(Modifier.width(8.dp))
        CartIconWithBadge(
            itemCount = shoppingCartQuantity,
            modifier = Modifier.clickable(true, onClick = onShoppingCartClick)
        )
    }
}