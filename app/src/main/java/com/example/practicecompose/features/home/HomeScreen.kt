package com.example.practicecompose.features.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicecompose.domain.commons.components.bottomBar.BottomNavigationBar

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        BottomNavigationBar(navHostController)
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navHostController = rememberNavController(),
    )
}