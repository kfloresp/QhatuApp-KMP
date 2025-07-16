package com.rgk.qhatu.utils

sealed class SearchType(val code: Int, val label: String) {
    data object Ean : SearchType(1, "Ean")
    data object Code : SearchType(2, "Código")
    data object Name : SearchType(3, "Nombre")
}