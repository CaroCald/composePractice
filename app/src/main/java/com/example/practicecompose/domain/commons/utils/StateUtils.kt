package com.example.practicecompose.domain.commons.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.domain.entities.generics.api.GenericApiState
import kotlinx.coroutines.flow.StateFlow

/**
 * Utility functions for handling state management and API results
 */
object StateUtils {
    
    /**
     * Processes API result and updates the generic API state
     */
    fun processApiResult(
        result: ApiResult<*>,
        onSuccess: () -> Unit = {},
        onError: () -> Unit = {},
        onLoading: () -> Unit = {}
    ): GenericApiState {
        return when (result) {
            is ApiResult.Loading -> {
                onLoading()
                GenericApiState(isLoading = result.isLoading)
            }
            is ApiResult.Success<*> -> {
                onSuccess()
                GenericApiState(isLoading = false)
            }
            is ApiResult.Error -> {
                onError()
                GenericApiState(isLoading = false, error = result.exception)
            }
            is ApiResult.ErrorGeneric<*> -> {
                onError()
                GenericApiState(isLoading = false, error = Throwable(result.exception))
            }
        }
    }
}

/**
 * Composable extension to collect state and handle API results
 */
@Composable
fun <T> collectApiState(
    stateFlow: StateFlow<ApiResult<T>>,
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable (Throwable) -> Unit = {},
    onLoading: @Composable () -> Unit = {}
) {
    val state by stateFlow.collectAsState()
    
    when (val currentState = state) {
        is ApiResult.Loading -> onLoading()
        is ApiResult.Success -> onSuccess(currentState.data)
        is ApiResult.Error -> onError(currentState.exception)
        is ApiResult.ErrorGeneric<*> -> onError(Throwable(currentState.exception))
    }
} 