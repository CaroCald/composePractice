package com.example.practicecompose.domain.use_cases.api

import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.user.UserFirebase
import com.example.practicecompose.data.remote.models.user.UserRequest
import com.example.practicecompose.data.remote.models.user.UserResponse
import com.example.practicecompose.data.remote.repositories.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for authentication operations
 * Follows clean architecture principles by working through repository pattern
 */
class AuthenticationUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    /**
     * Authenticate user with email and password
     * @param user User credentials
     * @return Flow of authentication result
     */
    fun login(user: UserRequest): Flow<ApiResult<UserResponse>> {
        return accountRepository.doLogin(user)
    }

    /**
     * Logout current user
     * @return Flow of logout result
     */
    suspend fun logout(): Flow<ApiResult<Unit>> {
        return accountRepository.closeSession()
    }

    /**
     * Get current user information
     * @return Flow of user information
     */
    fun getUserInfo(): Flow<UserFirebase> {
        return accountRepository.getUser()
    }

    /**
     * Check if user is currently logged in
     * @return Boolean indicating login status
     */
    fun isLoggedIn(): Boolean {
        return accountRepository.isLogged()
    }
}