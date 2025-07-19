package com.example.practicecompose.features.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicecompose.R
import com.example.practicecompose.domain.commons.components.buttons.PrimaryButton
import com.example.practicecompose.domain.commons.components.input.PrimaryInput
import com.example.practicecompose.domain.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.domain.commons.components.text.TextCustom
import com.example.practicecompose.domain.commons.utils.StateUtils
import com.example.practicecompose.features.auth.AuthViewModel
import com.example.practicecompose.navigation.NavigationItem

@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()
) {
    val loginState by authViewModel.loginState.collectAsState()
    val userState by authViewModel.userState.collectAsState()

    // Handle login success
    LaunchedEffect(loginState) {
        if (loginState is com.example.practicecompose.data.remote.ApiResult.Success) {
            navController.navigate(NavigationItem.MovieList.route) {
                popUpTo(NavigationItem.Login.route) { inclusive = true }
            }
        }
    }

    // Update API state
    LaunchedEffect(loginState) {
        val newApiState = StateUtils.processApiResult(
            result = loginState,
            onSuccess = { /* Navigation handled above */ },
            onError = { /* Error handled by ScaffoldCustom */ }
        )
        authViewModel.updateApiState(newApiState)
    }

    ScaffoldCustom(
        customBody = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header section
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 48.dp)
                ) {
                    TextCustom(
                        text = "Welcome Back",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextCustom(
                        text = "Sign in to continue",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }

                // Form section
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PrimaryInput(
                        text = authViewModel.formState.email,
                        title = stringResource(R.string.input_email),
                        isError = authViewModel.formState.emailError != null,
                        errorMessage = authViewModel.formState.emailError,
                        onTextChange = {
                            authViewModel.onEvent(LoginEvent.EmailChanged(it))
                        }
                    )
                    
                    PrimaryInput(
                        text = authViewModel.formState.password,
                        title = stringResource(R.string.input_password),
                        isError = authViewModel.formState.passwordError != null,
                        errorMessage = authViewModel.formState.passwordError,
                        onTextChange = {
                            authViewModel.onEvent(LoginEvent.PasswordChanged(it))
                        },
                        isPassword = true
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                PrimaryButton(
                    isEnable = authViewModel.formState.isValid,
                    text = stringResource(id = R.string.btn_login),
                    onClick = {
                        authViewModel.onEvent(LoginEvent.Submit)
                    }
                )
            }
        },
        onClickError = {
            authViewModel.restoreState()
        },
        isLoading = authViewModel.apiState.isLoading,
        hasError = authViewModel.apiState.error
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
    )
}