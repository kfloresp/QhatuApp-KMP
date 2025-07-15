package com.rgk.qhatu.ui.feature.auth
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.qhatu.domain.common.SyncResult
import com.rgk.qhatu.domain.model.User
import com.rgk.qhatu.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError

    private val _showPassword = MutableStateFlow(false)
    val showPassword: StateFlow<Boolean> = _showPassword

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

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

    fun toggleDialogVisibility() {
        _showDialog.value = !_showDialog.value
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
}


sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Authenticated(val user: User) : AuthState()
    data class Error(val exception: Throwable) : AuthState()
}