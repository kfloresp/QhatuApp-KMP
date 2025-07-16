package com.rgk.qhatu.data.feature.product.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val idInternal: Int,
    val id: String,
    val ean: String,
    val nombre: String,
    val categoria_id: String?,
    val tipo_almacenamiento_id: String?,
    val marca_id: String?,
    val precio_unitario: Double,
    val unidad_medida_id: String,
    val flag_lote: Int,
    val flag_activo: Int,
    val fecha_sincronizado: Long,
    val flag_sincronizado: Int
)