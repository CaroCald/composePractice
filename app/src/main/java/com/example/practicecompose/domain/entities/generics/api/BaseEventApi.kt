
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.ApiResult.*
import com.example.practicecompose.data.remote.CustomError
import com.example.practicecompose.domain.entities.generics.api.GenericApiState
import kotlin.reflect.full.memberProperties


@Composable
fun <T> baseEventApi(
    event: ApiResult<T>,
    onSuccess: @Composable (T) -> Unit,
    onError: (Throwable) -> Unit,
): GenericApiState {
    val apiState = remember { mutableStateOf(GenericApiState()) }
    when (event) {
        is Error -> {
            val exception = event.exception
            apiState.value = apiState.value.copy(isLoading = false, error = exception)
            if (exception.message != null) {
                onError(exception)
            }
        }
        is Loading -> {
            val isLoading = event.isLoading
            if(isLoading){
                apiState.value = apiState.value.copy(isLoading = true, error = Throwable())
            }
        }
        is Success -> {
            val result = event.data
            apiState.value = apiState.value.copy(isLoading = false)
            onSuccess(result)
        }

        is ErrorGeneric<*> -> {
            val err = event.data
            val errorMessage = err?.getField<String>("message") ?: ""
            val errorCode = err?.getField<Int>("code") ?: 1
            apiState.value = apiState.value.copy(isLoading = false, error = CustomError.ServerError(errorMessage,errorCode ))
        }
    }
    return apiState.value
}


@Throws(IllegalAccessException::class, ClassCastException::class)
inline fun <reified T> Any.getField(fieldName: String): T? {
    this::class.memberProperties.forEach { kCallable ->
        if (fieldName == kCallable.name) {
            return kCallable.getter.call(this) as T?
        }
    }
    return null
}