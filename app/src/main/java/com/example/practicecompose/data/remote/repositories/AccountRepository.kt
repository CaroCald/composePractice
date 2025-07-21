package com.example.practicecompose.data.remote.repositories

import com.example.practicecompose.data.local.managers.SessionManager
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.user.UserFirebase
import com.example.practicecompose.data.remote.models.user.UserRequest
import com.example.practicecompose.data.remote.models.user.UserResponse
import com.example.practicecompose.data.remote.services.AccountService
import com.example.practicecompose.domain.commons.utils.ErrorUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountService: AccountService,
    private val sessionManager: SessionManager
) {

    fun doLogin(user: UserRequest): Flow<ApiResult<UserResponse>> {
        return accountService.authenticate(email = user.email, password = user.password)
            .map { result ->
                result.fold(
                    onSuccess = {
                        sessionManager.saveSession(true)
                        ApiResult.Success(it)
                    },
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

    suspend fun closeSession(): Flow<ApiResult<Unit>> {
        sessionManager.clearSession()
        return accountService.signOut()
            .map { result ->
                result.fold(
                    onSuccess = {
                        ApiResult.Success(Unit)
                    },
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
        return accountService.currentUserFirebase
    }

    fun isLogged(): Boolean {
        return sessionManager.isSavedSession()
    }
}