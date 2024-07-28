package com.example.practicecompose.data.remote.repositories


import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.user.UserFirebase
import com.example.practicecompose.data.remote.models.user.UserRequest
import com.example.practicecompose.data.remote.models.user.UserResponse
import com.example.practicecompose.domain.use_cases.api.AuthenticationUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
) {

    fun doLogin(user: UserRequest): Flow<ApiResult<UserResponse>> {
        return authenticationUseCase.login(user)
    }

    suspend fun closeSession() {
        return authenticationUseCase.logout()
    }

    fun getUser(): Flow<UserFirebase> {
        return authenticationUseCase.getUserInfo()
    }

    fun isLogged(): Boolean {
        return authenticationUseCase.isLogged()
    }
}