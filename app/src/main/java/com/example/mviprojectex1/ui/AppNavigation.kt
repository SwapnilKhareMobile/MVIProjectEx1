package com.example.mviprojectex1.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigation(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "main"){
        composable("main"){
            MainAppScreen()
        }
    }
}