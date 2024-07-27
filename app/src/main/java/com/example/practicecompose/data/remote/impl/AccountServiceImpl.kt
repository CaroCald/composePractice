package com.example.practicecompose.data.remote.impl


import com.example.practicecompose.data.remote.models.generics.OkResponse
import com.example.practicecompose.data.remote.models.user.UserFirebase
import com.example.practicecompose.data.remote.models.user.UserResponse
import com.example.practicecompose.data.remote.services.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AccountService {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUserFirebase: Flow<UserFirebase>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let {
                        UserFirebase(it.uid, it.isAnonymous, it.email) } ?: UserFirebase())
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override fun authenticate(email: String, password: String): Flow<Result<UserResponse>> = flow {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            emit(Result.success(UserResponse(content = OkResponse(response = true)))) // Adjust OkResponse to match your needs
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun sendRecoveryEmail(email: String) : Flow<Result<OkResponse>> = flow {
        try{
            auth.sendPasswordResetEmail(email).await()
            emit(Result.success( OkResponse(response = true))) // Adjust OkResponse to match your needs
        }catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun signOut() : Flow<Result<OkResponse>> = flow {
        try{
            if (auth.currentUser!!.isAnonymous) {
                auth.currentUser!!.delete()
            }
            auth.signOut()
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

}