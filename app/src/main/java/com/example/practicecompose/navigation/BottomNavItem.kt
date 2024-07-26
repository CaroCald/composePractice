package com.example.practicecompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.practicecompose.R

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    @Composable
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = stringResource(R.string.my_profile),
                icon = Icons.Filled.Person,
                route = NavigationItem.Profile.route
            ),
            BottomNavigationItem(
                label = stringResource(R.string.movie_title),
                icon = Icons.Filled.Movie,
                route = NavigationItem.MovieList.route
            ),
        )
    }
}