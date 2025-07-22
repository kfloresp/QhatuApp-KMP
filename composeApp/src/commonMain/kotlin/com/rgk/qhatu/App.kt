package com.rgk.qhatu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rgk.qhatu.common.extension.navigateToAuthGraphWithPopUp
import com.rgk.qhatu.navigation.AppNavGraph
import com.rgk.qhatu.common.theme.QhatuTheme
import com.rgk.qhatu.feature.auth.domain.usecase.LogoutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel: MainViewModel = koinViewModel()
    val navController: NavHostController = rememberNavController()
    QhatuTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AppNavGraph(
                navController = navController,
                viewModel = viewModel,
                closeSession = {
                    viewModel.signOut()
                    navController.navigateToAuthGraphWithPopUp()
                })
        }
    }
}

class MainViewModel(private val logoutUseCase: LogoutUseCase) : ViewModel() {
    fun signOut() = viewModelScope.launch(Dispatchers.Main) {
        logoutUseCase.invoke()
    }
}