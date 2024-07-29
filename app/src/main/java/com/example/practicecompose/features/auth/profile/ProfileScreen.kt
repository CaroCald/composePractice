package com.example.practicecompose.features.auth.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicecompose.R
import com.example.practicecompose.domain.commons.components.bottomBar.BottomNavigationBar
import com.example.practicecompose.domain.commons.components.buttons.PrimaryButton
import com.example.practicecompose.domain.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.domain.commons.components.text.TextCustom
import com.example.practicecompose.domain.commons.components.toolbar.ToolBarCustom
import com.example.practicecompose.features.auth.AuthViewModel
import com.example.practicecompose.navigation.NavigationItem

@Composable
fun ProfileScreen(navHostController: NavHostController,
                  authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>(),) {

    LaunchedEffect(Unit){
        authViewModel.getUserInfo()
    }
    ScaffoldCustom(
        customToolBar = { ToolBarCustom(navController =navHostController ) },
        customBottomBar = { BottomNavigationBar(navHostController) },
        customBody = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextCustom(text =authViewModel.detailInfoUser().email ?: "")
                TextCustom(text = authViewModel.detailInfoUser().id)
                Spacer(modifier = Modifier.height(20.dp))
                PrimaryButton(text = stringResource(R.string.close_session), onClick = {
                    authViewModel.closeSession()
                    navHostController.navigate(route = NavigationItem.Splash.route)
                })
            }
        },
        isLoading = false
    )
}
@Preview
@Composable
fun HomeScreenPreview() {
    ProfileScreen(
        navHostController = rememberNavController(),
    )
}