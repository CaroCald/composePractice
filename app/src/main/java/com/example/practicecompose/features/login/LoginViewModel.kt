package com.example.practicecompose.features.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import baseEventApi
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.user.UserFirebase
import com.example.practicecompose.data.remote.models.user.UserRequest
import com.example.practicecompose.data.remote.models.user.UserResponse
import com.example.practicecompose.data.remote.repositories.AccountRepository
import com.example.practicecompose.domain.entities.generics.api.GenericApiState
import com.example.practicecompose.domain.use_cases.forms.ValidateEmailUseCase
import com.example.practicecompose.domain.use_cases.forms.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
): ViewModel(){

    private val _loginState = MutableStateFlow<ApiResult<UserResponse>>(ApiResult.Loading(false))
    private var loginState: StateFlow<ApiResult<UserResponse>> = _loginState.asStateFlow()

    private val _userState = MutableStateFlow(UserFirebase())
    val userState: StateFlow<UserFirebase> = _userState.asStateFlow()

    private val validateEmailUseCase = ValidateEmailUseCase()
    private val validatePasswordUseCase = ValidatePasswordUseCase()

    var formState by mutableStateOf(LoginState())

    var apiState by mutableStateOf(GenericApiState())

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
                validateForm()

            }
            is LoginEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                validateForm()
            }
            is LoginEvent.Submit -> {
                if (validateForm()) {
                    doLogin(UserRequest(formState.email, formState.password))
                }
            }
        }
    }

    @Composable
    fun EventApi(onSuccess: () -> Unit){
        val event by loginState.collectAsState()
        apiState = baseEventApi(event =event , onSuccess = {
            onSuccess()
        }, onError = {

        })
    }

    fun clear() {
        formState = LoginState()
        apiState = GenericApiState()
        loginState= MutableStateFlow<ApiResult<UserResponse>>(ApiResult.Error(Throwable()))
    }

    private fun validateForm(): Boolean {
        val emailResult = validateEmailUseCase.execute(formState.email)
        val passwordResult = validatePasswordUseCase.execute(formState.password)
        val success = emailResult.successful && passwordResult.successful

        formState = formState.copy(
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            isValid = success
        )

        return success
    }

    private fun doLogin(user: UserRequest) {
        viewModelScope.launch {
            accountRepository.doLogin(user)
                .collect { result ->
                    _loginState.value = result
                }
        }
    }
    fun getUserInfo() {
        viewModelScope.launch {
            accountRepository.getUser()
                .collect { result ->
                    _userState.value = result
                }
        }
    }
    fun closeSession() {
        viewModelScope.launch {
            accountRepository.closeSession()
        }
    }
    fun isLogged(): Boolean {
        return accountRepository.isLogged()
    }
}