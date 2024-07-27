package com.example.practicecompose.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicecompose.R
import com.example.practicecompose.commons.components.buttons.PrimaryButton
import com.example.practicecompose.commons.components.input.PrimaryInput
import com.example.practicecompose.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.commons.components.toolbar.ToolBarCustom
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.user.UserRequest
import com.example.practicecompose.navigation.NavigationItem


@Composable
fun LoginScreen(navController: NavHostController,
                authViewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),) {

    val loginState by authViewModel.loginState.collectAsState()
    var email by remember { mutableStateOf("caro@gmail.com") }
    var password by remember { mutableStateOf("123456") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(Throwable()) }

    ScaffoldCustom(
        customBody = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.title_welcome))
                Spacer(modifier = Modifier.height(8.dp))
                PrimaryInput(text = email,
                    title = stringResource(R.string.input_email ) ,
                    onTextChange = { email = it },)
                Spacer(modifier = Modifier.height(8.dp))
                PrimaryInput(title = stringResource(R.string.input_password),
                    text = password,
                    onTextChange = {
                        password = it
                    },
                    isPassword = true,)
                Spacer(modifier = Modifier.height(20.dp))
                PrimaryButton(text = stringResource(id = R.string.btn_login),
                    onClick = {
                    authViewModel.doLogin(UserRequest(email, password))
                })
            }
            when (loginState) {
                is ApiResult.Loading -> {
                    isLoading = (loginState as ApiResult.Loading).isLoading
                }
                is ApiResult.Success -> {
                    val result = (loginState as ApiResult.Success).data.content
                    if(result!=null){
                        isLoading = false
                        navController.navigate(route = NavigationItem.MovieList.route)
                    }
                }
                is ApiResult.Error -> {
                    isLoading = false
                    val exception = (loginState as ApiResult.Error).exception
                    if(exception.message!=null){
                        error=exception
                    }
                }
            }
        },
        isLoading = isLoading,
        hasError = error

    )
}
@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
    )
}