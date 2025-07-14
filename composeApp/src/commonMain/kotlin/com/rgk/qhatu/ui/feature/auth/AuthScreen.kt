package com.rgk.qhatu.ui.feature.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavController
import com.rgk.qhatu.ui.components.AlertMessageDialog
import com.rgk.qhatu.ui.components.EmailField
import com.rgk.qhatu.ui.components.LoadingView
import com.rgk.qhatu.ui.components.PrimaryButton
import com.rgk.qhatu.ui.components.PasswordField
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.bt_start_login
import qhatuapp.composeapp.generated.resources.ic_leaf
import qhatuapp.composeapp.generated.resources.tx_acept
import qhatuapp.composeapp.generated.resources.tx_error
import qhatuapp.composeapp.generated.resources.tx_title_login

@Composable
fun AuthScreen(
    uiState: AuthState,
    emailValue: String,
    passwordValue: String,
    emailError: String?,
    passwordError: String?,
    showPassword: Boolean,
    showDialog: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    togglePasswordVisibility: () -> Unit,
    toggleDialogVisibility: () -> Unit,
    onLoginClick: () -> Unit,
    navigateToHome: () -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()
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
            text = stringResource(Res.string.tx_title_login),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        EmailField(
            emailValue,
            emailError,
            onEmailChange,
            emailFocusRequester,
            keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() })
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordField(
            value = passwordValue,
            error = passwordError,
            onValueChange = onPasswordChange,
            visible = showPassword,
            onToggleVisibility = togglePasswordVisibility,
            focusRequester = passwordFocusRequester,
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                onLoginClick.invoke()
            })
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            text = stringResource(Res.string.bt_start_login),
            onClick = {
                keyboardController?.hide()
                onLoginClick.invoke()
            }
        )
    }
    LaunchedEffect(uiState) {
        if (uiState is AuthState.Authenticated) {
            navigateToHome.invoke()
        }
    }
    if (uiState is AuthState.Error && showDialog) {
        AlertMessageDialog(
            title = stringResource(Res.string.tx_error),
            message = uiState.exception.message.orEmpty(),
            confirmText = stringResource(Res.string.tx_acept),
            onConfirm = { toggleDialogVisibility() }
        )
    }
    if (uiState is AuthState.Loading) {
        LoadingView()
    }
}

