package com.rgk.qhatu.common.exception


fun roomException(e: Throwable): Throwable {
    val message = e.message?.lowercase() ?: ""

    return when {
        "unique" in message ->
            QhatuException.ExceptionRoom("Error: ya existe un elemento con ese identificador (clave única).$e")

        "not null" in message ->
            QhatuException.ExceptionRoom("Error: faltan campos obligatorios.$e")

        "mismatch" in message || "datatype" in message ->
            QhatuException.ExceptionRoom("Error: tipo de dato inválido en alguna columna.$e")

        "constraint" in message ->
            QhatuException.ExceptionRoom("Error: violación de restricción de integridad.$e")

        else ->
            QhatuException.ExceptionRoom("$e")
    }
}


enum class RoomOperationType {
    INSERT, UPDATE, DELETE
}

fun validateRoomRowCount(row: Int, operation: RoomOperationType) {
    when (operation) {
        RoomOperationType.INSERT -> {
            if (row == -1) {
                throw QhatuException.ExceptionRoom("Falló la inserción: no se insertó ningún registro.")
            }
        }

        RoomOperationType.UPDATE -> {
            when {
                row == 0 -> throw QhatuException.ExceptionRoom("Falló la actualización: no se actualizó ningún registro.")
                row > 1 -> throw QhatuException.ExceptionRoom("Actualización ambigua: se actualizaron múltiples registros ($row).")
            }
        }

        RoomOperationType.DELETE -> {
            if (row == 0) {
                throw QhatuException.ExceptionRoom("Falló la eliminación: no se eliminó ningún registro.")
            }
        }
    }
}
