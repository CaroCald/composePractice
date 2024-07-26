package com.example.practicecompose.commons.components.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.practicecompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarCustom(
    title: @Composable() (() -> Unit?)? = null,
    navController: NavHostController,
    hasBackButton: Boolean = false
){

    TopAppBar(
        navigationIcon = {
            if(hasBackButton){
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    ),
                    onClick = {
                    navController.popBackStack()
                }) {
                    Icon(

                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description",
                    )
                }
            }

        },

        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
        ),
        title = { title?.let { it() } }
    )
}
