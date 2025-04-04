package com.example.practicecompose.data.remote.services

import com.example.practicecompose.domain.entities.generics.api.OkResponse
import com.example.practicecompose.data.remote.models.user.UserFirebase
import com.example.practicecompose.data.remote.models.user.UserResponse
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val currentUserFirebase: Flow<UserFirebase>

    fun authenticate(email: String, password: String): Flow<Result<UserResponse>>
    fun sendRecoveryEmail(email: String): Flow<Result<OkResponse>>
    fun signOut(): Flow<Result<OkResponse>>
}