package com.mz_dev.prueba_tecnica.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mz_dev.prueba_tecnica.ui.view.SplashScreen
import com.mz_dev.prueba_tecnica.ui.view.MainScreen
import com.mz_dev.prueba_tecnica.ui.viewmodel.MainViewModel

@Composable
fun AppNavigation() {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.SplashScreen.route){
            SplashScreen(navController, viewModel)
        }
        composable(route = AppScreens.MainScreen.route){
            MainScreen(viewModel)
        }
    }

}