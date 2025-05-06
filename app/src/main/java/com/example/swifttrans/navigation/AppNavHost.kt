package com.example.swifttrans.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swifttrans.navigation.ROUTE_REGISTER
import com.example.swifttrans.navigation.ROUTE_SPLASH
//import com.example.swifttrans.ui.theme.screens.SplashScreen
//import com.example.swifttrans.ui.theme.screens.dashboard.DashboardScreen
import com.example.swifttrans.ui.theme.screens.login.LoginScreen
import com.example.swifttrans.ui.theme.screens.register.RegisterScreen
//import com.example.swifttrans.ui.theme.screens.home.homeScreen


@Composable
fun AppNavHost(navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_REGISTER){
    NavHost(navController=navController,startDestination=startDestination){
        composable(ROUTE_SPLASH){ {
            navController.navigate(ROUTE_REGISTER){
                popUpTo(ROUTE_SPLASH){inclusive=true}} } }
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
//        composable(ROUTE_LOGIN) { LoginScreen(navController) }

    }
}