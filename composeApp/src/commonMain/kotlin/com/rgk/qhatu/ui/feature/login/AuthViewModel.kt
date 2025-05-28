package com.rgk.qhatu.ui.feature.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.User
import com.rgk.qhatu.domain.usecase.AuthUseCase
import com.rgk.qhatu.ui.navigation.RouteNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _emailError = mutableStateOf<String?>(null)
    val emailError: State<String?> = _emailError

    private val _passwordError = mutableStateOf<String?>(null)
    val passwordError: State<String?> = _passwordError

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        _emailError.value = null
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _passwordError.value = null
    }

    fun togglePasswordVisibility() {
        _showPassword.value = !_showPassword.value
    }

    fun hideDialog() {
        _showDialog.value = false
    }

    fun clearCredentials() {
        _email.value = ""
        _password.value = ""
        _emailError.value = null
        _passwordError.value = null
        _showPassword.value = false
        _authState.value = AuthState.Idle
    }

    fun onLoginClick() {
        if (_email.value.isBlank()) {
            _emailError.value = "El correo no puede estar vacío"
            return
        }
        if (!isValidEmail(_email.value)) {
            _emailError.value = "El correo electrónico inválido"
            return
        }
        if (_password.value.isBlank()) {
            _passwordError.value = "La contraseña no puede estar vacía"
            return
        }

        _authState.value = AuthState.Loading

        viewModelScope.launch {
            @Suppress("SOME_SONAR_RULE")
                val result = authUseCase.login(_email.value, _password.value)
                when (result){
                    is SyncResult.Error -> {
                        _showDialog.value = true
                        _authState.update { AuthState.Error(result.exception) }
                    }
                    is SyncResult.Success -> _authState.update { AuthState.Authenticated(result.data) }
                }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    suspend fun logout() {
        clearCredentials()
        authUseCase.logout()
        _authState.update { AuthState.Idle }
    }

    fun getCurrentUser(): User? {
        return authUseCase.currentUser()
    }

    fun goToHome(navController: NavController) {
        navController.navigate(RouteNavigation.Home.src) {
            popUpTo(RouteNavigation.Login.src) { inclusive = true }
        }
    }
}


sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Authenticated(val user: User) : AuthState()
    data class Error(val exception: Throwable) : AuthState()
}