package com.rgk.qhatu.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rgk.qhatu.ui.feature.login.AuthViewModel
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.app_name
import qhatuapp.composeapp.generated.resources.ic_leaf

@Composable
fun SplashScreen(navController: NavController, viewModel: AuthViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        delay(2000)
        if (viewModel.getCurrentUser() != null) {
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(Res.drawable.ic_leaf),
                contentDescription = "",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = stringResource(Res.string.app_name), fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
    }
}
