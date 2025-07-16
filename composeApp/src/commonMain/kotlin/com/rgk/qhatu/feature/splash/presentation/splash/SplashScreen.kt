package com.rgk.qhatu.feature.splash.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import qhatuapp.composeapp.generated.resources.Res
import qhatuapp.composeapp.generated.resources.app_name
import qhatuapp.composeapp.generated.resources.ic_leaf

@Composable
fun SplashScreen(
    uiState: SplashUiState,
    navigateToAuthGraph: () -> Unit,
    navigateToHomeGraph: () -> Unit
) {
    when (uiState) {
        is SplashUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${uiState.message}")
            }
        }

        SplashUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(Res.drawable.ic_leaf),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = stringResource(Res.string.app_name),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        SplashUiState.NavigateToHome -> navigateToHomeGraph.invoke()
        SplashUiState.NavigateToLogin -> navigateToAuthGraph.invoke()
    }
}
