package com.example.practicecompose.domain.entities.generics.api
import android.content.Context
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.constants.Constants.Companion.API_INTERNET_CODE
import com.example.practicecompose.data.remote.constants.Constants.Companion.API_INTERNET_MESSAGE
import com.example.practicecompose.domain.commons.utils.Utils
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties


inline fun <reified T> toResultFlow(context: Context, crossinline call: suspend () -> Response<T>?): Flow<ApiResult<T>> {
    return flow {
        val isInternetConnected = Utils.hasInternetConnection(context)
        if (isInternetConnected) {
            emit(ApiResult.Loading( true))
            val callResponse = call()
            callResponse?.let { response ->
                try {
                    if (callResponse.isSuccessful && callResponse.body() != null) {
                        callResponse.body()?.let {
                            emit(ApiResult.Success(it))
                        }
                    } else {
                        val errorResponse: T? = parseError(response)
                        emit(ApiResult.ErrorGeneric(errorResponse, response.message()))
                    }
                } catch (e: Exception) {
                    val model = setResponseStatus(ResponseError::class.java, API_INTERNET_CODE, API_INTERNET_MESSAGE)
                    emit(ApiResult.ErrorGeneric(model, e.toString()))
                }
            }
        } else {
            val model = setResponseStatus(ResponseError::class.java,
                API_INTERNET_CODE, API_INTERNET_MESSAGE
            )
            emit(ApiResult.ErrorGeneric(model, API_INTERNET_MESSAGE))
        }
    }.flowOn(Dispatchers.IO)
}

inline fun <reified T> parseError(response: Response<*>): T? {
    return try {
        response.errorBody()?.string()?.let {
            Gson().fromJson(it, T::class.java)
        }
    } catch (e: IOException) {
        e.printStackTrace()
        null
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


inline fun <reified T> setResponseStatus(
    clazz: Class<T>,
    errorCode: String?,
    message: String?
): T? {
    return try {
        val instance = clazz.getDeclaredConstructor().newInstance()
        val properties = instance!!::class.memberProperties
        for (property in properties) {
            if (property is KMutableProperty<*>) {
                when (property.name) {
                    "code" -> {
                        val codeValue = when (property.returnType.classifier) {
                            Int::class -> errorCode?.toIntOrNull()
                            else -> errorCode
                        }
                        property.setter.call(instance, codeValue)
                    }
                    "message" -> property.setter.call(instance, message)
                }
            }
        }
        instance
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

data class ResponseError(
    var code: Int? = null,
    var message: String? = null
)
