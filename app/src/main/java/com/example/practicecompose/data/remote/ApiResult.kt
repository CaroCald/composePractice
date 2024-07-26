package com.example.practicecompose.data.remote


sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Loading(val isLoading: Boolean) : ApiResult<Nothing>()
    data class Error(val exception: Throwable) : ApiResult<Nothing>()
}


sealed class CustomError : Exception() {
    data class NetworkError(override val message: String, val code: Int) : CustomError()
    data class ServerError(override val message: String, val code: Int) : CustomError()
    data class ValidationError(override val message: String, val code: Int) : CustomError()
    data class UnknownError(override val message: String, val code: Int) : CustomError()
}