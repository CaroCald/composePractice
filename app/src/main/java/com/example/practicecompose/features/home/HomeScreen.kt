package com.example.practicecompose.features.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicecompose.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.commons.components.toolbar.ToolBarCustom

@Composable
fun HomeScreen(navHostController: NavHostController) {
    ScaffoldCustom(
        customToolBar = { ToolBarCustom() },
        customBody = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Hello, World!")

            }
        },
        isLoading = false
    )
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navHostController = rememberNavController(),
    )
}