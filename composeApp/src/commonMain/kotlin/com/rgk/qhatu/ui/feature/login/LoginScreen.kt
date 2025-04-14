package com.rgk.qhatu.ui.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rgk.qhatu.di.AppModule
import com.rgk.qhatu.ui.components.AlertMessageDialog
import com.rgk.qhatu.ui.components.EmailField
import com.rgk.qhatu.ui.components.LoadingProgress
import com.rgk.qhatu.ui.components.LoginButton
import com.rgk.qhatu.ui.components.PasswordField
import com.rgk.qhatu.utils.FirebaseAuthErrorHandler
import org.jetbrains.compose.resources.painterResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.ic_leaf

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = AppModule.authViewModel
) {
    val email by authViewModel.email
    val password by authViewModel.password
    val emailError by authViewModel.emailError
    val passwordError by authViewModel.passwordError
    val showPassword by authViewModel.showPassword
    val showDialog by authViewModel.showDialog
    val authState by authViewModel.authState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()
    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
            authViewModel.clearCredentials()
        }
    }
    if (authState is AuthState.Error && showDialog) {
        AlertMessageDialog(
            title = "Error",
            message = FirebaseAuthErrorHandler.handleException((authState as AuthState.Error).exception),
            confirmText = "Aceptar",
            onConfirm = { authViewModel.hideDialog() }
        )
    }
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp)
            .imePadding()
            .pointerInput(Unit) { detectTapGestures { keyboardController?.hide() } },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(Res.drawable.ic_leaf),
            contentDescription = "",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Iniciar Sesión",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        EmailField(
            email,
            emailError,
            authViewModel::onEmailChange,
            emailFocusRequester,
            keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() })
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordField(
            password,
            passwordError,
            showPassword,
            authViewModel::onPasswordChange,
            authViewModel::togglePasswordVisibility,
            passwordFocusRequester,
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                authViewModel.onLoginClick()
            })
        )
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(
            onClick = {
                keyboardController?.hide()
                authViewModel.onLoginClick()
            }
        )
    }
    if (authState is AuthState.Loading) {
        LoadingProgress()
    }
}

