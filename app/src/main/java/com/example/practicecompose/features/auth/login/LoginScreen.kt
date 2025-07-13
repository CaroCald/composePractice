package com.example.practicecompose.features.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.practicecompose.features.auth.AuthViewModel
import com.example.practicecompose.navigation.NavigationItem


@Composable
fun LoginScreen(navController: NavHostController,
                authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()) {

    ScaffoldCustom(
        customBody = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.backgroud_image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds, // fill the entire screen nicely
                    modifier = Modifier.height(500.dp).fillMaxWidth()
                )
               // Spacer(modifier = Modifier.height(8.dp))
                PrimaryInput(
                    text = authViewModel.formState.email,
                    title = stringResource(R.string.input_email ),
                    isError = authViewModel.formState.emailError != null,
                    errorMessage = authViewModel.formState.emailError ,
                    onTextChange = {
                        authViewModel.onEvent(LoginEvent.EmailChanged(it))
                    })
                Spacer(modifier = Modifier.height(8.dp))
                PrimaryInput(
                    text = authViewModel.formState.password,
                    title = stringResource(R.string.input_password),
                    isError = authViewModel.formState.passwordError != null,
                    errorMessage = authViewModel.formState.passwordError ,
                    onTextChange = {
                        authViewModel.onEvent(LoginEvent.PasswordChanged(it))
                    },
                    isPassword = true,)
                Spacer(modifier = Modifier.height(20.dp))
                PrimaryButton(
                    isEnable = authViewModel.formState.isValid,
                    text = stringResource(id = R.string.btn_login),
                    onClick = {
                        authViewModel.onEvent(LoginEvent.Submit)
                    })
                Spacer(modifier = Modifier.height(20.dp))
            }
            authViewModel.EventApi(onSuccess = {
                navController.navigate(NavigationItem.MovieList.route)
            })
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