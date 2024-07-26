package com.example.practicecompose.navigation

import androidx.navigation.NavHostController

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.practicecompose.features.home.HomeScreen
import com.example.practicecompose.features.login.LoginScreen
import com.example.practicecompose.features.movies.MovieScreen
import com.example.practicecompose.features.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route,
    ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationItem.Login.route) {
            LoginScreen(navController)
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationItem.MovieList.route) {
            MovieScreen(navController)
        }

    }
}