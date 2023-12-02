package com.mz_dev.prueba_tecnica.ui.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mz_dev.prueba_tecnica.ui.navigation.AppScreens
import com.mz_dev.prueba_tecnica.ui.view.component.Splash
import com.mz_dev.prueba_tecnica.ui.viewmodel.MainViewModel

@Composable
fun SplashScreen(navController: NavHostController, viewModel: MainViewModel) {
    val state by viewModel.uiState.collectAsState()
    if (state.loading) {
        LaunchedEffect(key1 = true) {
            navController.popBackStack()
            navController.navigate(AppScreens.MainScreen.route)
        }
    }
    Spacer(modifier = Modifier.size(10.dp))
    Splash()
}