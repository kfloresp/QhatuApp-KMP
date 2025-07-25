package com.rgk.qhatu.feature.setting.presentation.sync.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backup
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.tx_audit_title
import qhatuapp.composeapp.generated.resources.tx_brands_title
import qhatuapp.composeapp.generated.resources.tx_categories_title
import qhatuapp.composeapp.generated.resources.tx_configuration_title
import qhatuapp.composeapp.generated.resources.tx_customer_title
import qhatuapp.composeapp.generated.resources.tx_payment_customer_title
import qhatuapp.composeapp.generated.resources.tx_paymnet_transaction_title
import qhatuapp.composeapp.generated.resources.tx_product_title
import qhatuapp.composeapp.generated.resources.tx_profile_title
import qhatuapp.composeapp.generated.resources.tx_sale_detail_title
import qhatuapp.composeapp.generated.resources.tx_sale_title
import qhatuapp.composeapp.generated.resources.tx_sync_title
import qhatuapp.composeapp.generated.resources.tx_units_title


data class SyncsOption(
    val type: SyncType,
    val icon: ImageVector,
    val title: StringResource,
    val subtitle: String? = null,
    val loading: Boolean = false,
)

fun getSyncsOptions(): List<SyncsOption> {
    return listOf(
        SyncsOption(
            type = SyncType.STORE,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_profile_title,
        ),
        SyncsOption(
            type = SyncType.CATEGORY,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_categories_title,
        ),
        SyncsOption(
            type = SyncType.BRAND,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_brands_title,
        ),
        SyncsOption(
            type = SyncType.UNIT_MEASURE,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_units_title,
        ),
        SyncsOption(
            type = SyncType.UNIT_MEASURE,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_sync_title,
        ),
        SyncsOption(
            type = SyncType.CUSTOMER,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_customer_title,
        ),
        SyncsOption(
            type = SyncType.PAYMENT_CUSTOMER,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_payment_customer_title,
        ),
        SyncsOption(
            type = SyncType.PAYMENT_TRANSACTION,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_paymnet_transaction_title,
        ),
        SyncsOption(
            type = SyncType.PRODUCT,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_product_title,
        ),
        SyncsOption(
            type = SyncType.SALE,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_sale_title,
        ),
        SyncsOption(
            type = SyncType.SALE_DETAIL,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_sale_detail_title,
        ),
        SyncsOption(
            type = SyncType.CONFIGURATION,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_configuration_title,
        ),
        SyncsOption(
            type = SyncType.AUDIT,
            icon = Icons.Outlined.Backup,
            title = Res.string.tx_audit_title,
        ),
    )
}
