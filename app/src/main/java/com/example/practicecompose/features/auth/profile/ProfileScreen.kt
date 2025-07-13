package com.example.practicecompose.features.auth.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                   modifier = Modifier.align(alignment = Alignment.End).clickable {
                       authViewModel.closeSession()
                       navHostController.navigate(route = NavigationItem.Splash.route) {
                           popUpTo(0) // Optional: Clear back stack
                       }
                   },
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    tint = Color.White,
                    contentDescription = "Localized description",
                )

                Spacer(modifier = Modifier.height(16.dp))
                // Profile Image
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // User Email
                TextCustom(
                    text = authViewModel.detailInfoUser().email ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )

                // User ID
                TextCustom(
                    text = "ID: ${authViewModel.detailInfoUser().email}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))


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