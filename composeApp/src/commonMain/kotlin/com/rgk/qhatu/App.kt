package com.rgk.qhatu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.rgk.qhatu.presentation.navigation.AppNavGraph
import com.rgk.qhatu.utils.QhatuTheme

@Composable
fun App() {
    QhatuTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AppNavGraph()
        }
    }
}