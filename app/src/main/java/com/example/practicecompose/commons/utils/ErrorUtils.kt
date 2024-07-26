package com.example.practicecompose.commons.utils

import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.CustomError
import java.io.IOException
object ErrorUtils {
    fun handleException(exception: Throwable): ApiResult.Error {
        val customError = when (exception) {
            is IOException -> CustomError.NetworkError("Network error occurred: ${exception.message}", code =ErrorsCodes.NETWORK.code )
            is IllegalArgumentException -> CustomError.ValidationError("Validation error: ${exception.message}", code =ErrorsCodes.ILLEGAL.code)
            is CustomError.ServerError -> CustomError.ValidationError("Validation error: ${exception.message}", code =exception.code)
            else -> CustomError.UnknownError("An unknown error occurred: ${exception.message}" , code =ErrorsCodes.UNKNOWN.code)
        }
        return ApiResult.Error(customError)
    }
    enum class ErrorsCodes(val code: Int) {
        NETWORK(1),
        UNKNOWN(2),
        ILLEGAL(3);

        companion object {
            fun fromCode(code: Int): ErrorsCodes? {
                return entries.find { it.code == code }
            }
        }
    }
}