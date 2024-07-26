package com.example.practicecompose.navigation

enum class Screen {
    HOME,
    LOGIN,
    SPLASH,
    MOVIE_LIST,
    PROFILE
}
sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Login : NavigationItem(Screen.LOGIN.name)
    data object Splash : NavigationItem(Screen.SPLASH.name)
    data object MovieList : NavigationItem(Screen.MOVIE_LIST.name)
    data object Profile : NavigationItem(Screen.PROFILE.name)
}