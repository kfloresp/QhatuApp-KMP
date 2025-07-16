package com.rgk.qhatu.presentation.feature.auth.ui.auth

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object AuthDestination

internal fun NavGraphBuilder.authDestination(
    navigateToHome: () -> Unit,
) {
    composable<AuthDestination> {
        val viewModel: AuthViewModel = koinViewModel()
        val uiState by viewModel.authState.collectAsState()
        val emailValue by viewModel.email.collectAsState()
        val passwordValue by viewModel.password.collectAsState()
        val emailError by viewModel.emailError.collectAsState()
        val passwordError by viewModel.passwordError.collectAsState()
        val showPassword by viewModel.showPassword.collectAsState()
        val showDialog by viewModel.showDialog.collectAsState()
        AuthScreen(
            uiState = uiState,
            emailValue = emailValue,
            passwordValue = passwordValue,
            emailError = emailError,
            passwordError = passwordError,
            showPassword = showPassword,
            showDialog = showDialog,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            togglePasswordVisibility = viewModel::togglePasswordVisibility,
            onLoginClick = viewModel::onLoginClick,
            toggleDialogVisibility = viewModel::toggleDialogVisibility,
            navigateToHome = { navigateToHome.invoke() }
        )
    }
}