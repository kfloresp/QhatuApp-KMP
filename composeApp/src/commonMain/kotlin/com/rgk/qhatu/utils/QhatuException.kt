package com.rgk.qhatu.utils

sealed class QhatuException : Throwable() {

    object InvalidSearchType : QhatuException() {
        override val message: String = "Tipo de búsqueda no válida"
    }

    object ProductNotFound : QhatuException() {
        override val message: String = "Producto no encontrado"
    }

    object NetworkUnavailable : QhatuException() {
        override val message: String = "No hay conexión a internet"
    }

    object UnauthorizedAccess : QhatuException() {
        override val message: String = "Acceso no autorizado"
    }

    data class Unexpected(val errorMessage: String) : QhatuException() {
        override val message: String = errorMessage
    }
}
