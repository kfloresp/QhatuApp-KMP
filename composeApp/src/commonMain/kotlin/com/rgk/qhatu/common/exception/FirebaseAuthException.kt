package com.rgk.qhatu.common.exception

import dev.gitlive.firebase.FirebaseNetworkException
import dev.gitlive.firebase.FirebaseTooManyRequestsException
import dev.gitlive.firebase.auth.FirebaseAuthEmailException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidUserException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException

object FirebaseAuthException {
    fun handleException(throwable: Throwable): Throwable {
        return when (throwable) {
            is FirebaseAuthInvalidUserException -> QhatuException.Exception("No existe una cuenta con este correo")
            is FirebaseAuthInvalidCredentialsException -> QhatuException.Exception(handleCredentialsError(throwable))
            is FirebaseAuthUserCollisionException -> QhatuException.Exception("Esta cuenta ya está registrada")
            is FirebaseNetworkException -> QhatuException.Exception("Error de conexión. Verifica tu internet")
            is FirebaseTooManyRequestsException -> QhatuException.Exception("Demasiados intentos. Intenta más tarde")
            is FirebaseAuthEmailException -> QhatuException.Exception("Error con el correo electrónico")
            is FirebaseAuthWeakPasswordException -> QhatuException.Exception("La contraseña es muy débil")
            else -> QhatuException.Exception("Error durante la autenticación. Intenta nuevamente")
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