package com.rgk.qhatu.utils

import dev.gitlive.firebase.FirebaseNetworkException
import dev.gitlive.firebase.FirebaseTooManyRequestsException
import dev.gitlive.firebase.auth.FirebaseAuthEmailException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidUserException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException

object FirebaseAuthErrorHandler {
    fun handleException(throwable: Throwable): String {
        return when (throwable) {
            is FirebaseAuthInvalidUserException -> "No existe una cuenta con este correo"
            is FirebaseAuthInvalidCredentialsException -> handleCredentialsError(throwable)
            is FirebaseAuthUserCollisionException -> "Esta cuenta ya está registrada"
            is FirebaseNetworkException -> "Error de conexión. Verifica tu internet"
            is FirebaseTooManyRequestsException -> "Demasiados intentos. Intenta más tarde"
            is FirebaseAuthEmailException -> "Error con el correo electrónico"
            is FirebaseAuthWeakPasswordException -> "La contraseña es muy débil"
            else -> "Error durante la autenticación. Intenta nuevamente"
        }
    }
    private fun handleCredentialsError(e: FirebaseAuthInvalidCredentialsException): String {
        return when {
            e.message?.contains("email") == true -> "Formato de correo inválido"
            e.message?.contains("password") == true -> "Contraseña incorrecta"
            else -> "Credenciales inválidas"
        }
    }
}