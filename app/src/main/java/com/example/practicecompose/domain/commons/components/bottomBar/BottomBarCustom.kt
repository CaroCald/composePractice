package com.example.practicecompose.domain.commons.components.bottomBar

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.practicecompose.domain.commons.components.text.TextCustom
import com.example.practicecompose.navigation.BottomNavigationItem

@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 10.dp
    ) {
        BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
            val selected = navigationItem.route == currentDestination?.route
            NavigationBarItem(
                selected = selected,
                label = {
                    TextCustom(
                        navigationItem.label,
                        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                    )
                },

                icon = {
                    Icon(
                        navigationItem.icon,
                        contentDescription = navigationItem.label,
                        tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                    )
                },
                colors = NavigationBarItemDefaults.colors(

                    indicatorColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    navController.navigate(navigationItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }


}