package com.rgk.qhatu.feature.product.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductModel(
    @SerialName("id")
    val id: String = "",
    @SerialName("ean")
    val ean: String = "",
    @SerialName("nombre")
    val name: String = "",
    @SerialName("categoria_id")
    val categoryId: String? = null,
    @SerialName("tipo_almacenamiento_id")
    val storageTypeId: String? = null,
    @SerialName("marca_id")
    val brandId: String? = null,
    @SerialName("precio_unitario")
    val unitPrice: Double = 0.0,
    @SerialName("unidad_medida_id")
    val unitMeasureId: String = "",
    @SerialName("flag_lote")
    val isBatch: Boolean = false,
    @SerialName("flag_activo")
    val isActive: Boolean = false,
    @SerialName("fecha_sincronizado")
    val syncedDate: Long = 0,
    @SerialName("flag_sincronizado")
    val isSynced: Boolean = false,
    @SerialName("flag_eliminado")
    val isDeleted: Boolean = false,
    @SerialName("lastUpdated")
    val lastUpdated: Long = 0,
)