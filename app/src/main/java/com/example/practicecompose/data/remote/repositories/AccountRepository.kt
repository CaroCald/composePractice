package com.example.practicecompose.data.remote.repositories


import com.example.practicecompose.commons.utils.ErrorUtils
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.generics.OkResponse
import com.example.practicecompose.data.remote.models.user.UserFirebase
import com.example.practicecompose.data.remote.models.user.UserRequest
import com.example.practicecompose.data.remote.models.user.UserResponse
import com.example.practicecompose.data.remote.services.AccountService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val service: AccountService
) {
    fun doLogin(user: UserRequest): Flow<ApiResult<UserResponse>> {
        return service.authenticate(email = user.email, password = user.password)
            .map { result ->
                result.fold(
                    onSuccess = { ApiResult.Success(it) },
                    onFailure = {
                        ErrorUtils.handleException(it)
                    }
                )
            }
            .onStart { emit(ApiResult.Loading(true)) }
            .catch { error ->
                emit(ErrorUtils.handleException(error))
            }
    }

    fun getUser(): Flow<UserFirebase> {
        return service.currentUserFirebase
    }

    suspend fun closeSession() : Flow<ApiResult<OkResponse>> {
        return service.signOut() .map { result ->
            result.fold(
                onSuccess = { ApiResult.Success(it) },
                onFailure = {
                    ErrorUtils.handleException(it)
                }
            )
        }
            .onStart { emit(ApiResult.Loading(true)) }
            .catch { error ->
                emit(ErrorUtils.handleException(error))
            }
    }
}