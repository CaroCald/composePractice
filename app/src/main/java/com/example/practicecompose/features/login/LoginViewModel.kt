package com.example.practicecompose.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.user.UserRequest
import com.example.practicecompose.data.remote.models.user.UserResponse
import com.example.practicecompose.data.remote.repositories.AccountRepository
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
    val loginState: StateFlow<ApiResult<UserResponse>> = _loginState.asStateFlow()


    fun doLogin(user: UserRequest) {
        viewModelScope.launch {
            accountRepository.doLogin(user)
                .collect { result ->
                    _loginState.value = result
                }
        }
    }
}