package com.example.practicecompose.commons.components.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.practicecompose.R
import com.example.practicecompose.commons.components.alerts.CustomAlertDialog
import com.example.practicecompose.commons.components.bottomBar.BottomBarCustom
import com.example.practicecompose.commons.components.loading.Loading
import com.example.practicecompose.commons.components.toolbar.ToolBarCustom
import com.example.practicecompose.data.remote.CustomError


@Composable
fun ScaffoldCustom(
    customToolBar: (@Composable () -> Unit)? = null,
    customBottomBar: (@Composable () -> Unit)? = null,
    customFloatingButton: (@Composable () -> Unit)? = null,
    customBody: @Composable () -> Unit ,
    isLoading: Boolean,
    hasError : Any? =null
) {
    Box {
        Scaffold(
            topBar =  customToolBar ?: {},
            bottomBar = customBottomBar ?: {},
            floatingActionButton =  customFloatingButton ?: {}
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                customBody()
            }
        }
        if (isLoading) {
            Loading()
        }
        if(hasError is CustomError){
            val shouldShowDialog = remember { mutableStateOf(false) }
            shouldShowDialog.value = true
            CustomAlertDialog(
                shouldShowDialog = shouldShowDialog,
                message = hasError.message ?: stringResource(R.string.error_generic)
            )
        }
    }

}