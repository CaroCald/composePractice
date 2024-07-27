
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.domain.use_cases.api.GenericApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun <T> baseEventApi(
    event: ApiResult<T>,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit,
): GenericApiState {
    val apiState = remember { mutableStateOf(GenericApiState()) }
    when (event) {
        is ApiResult.Error -> {
            val exception = event.exception
            apiState.value = apiState.value.copy(isLoading = false, error = exception)
            if (exception.message != null) {
                onError(exception)
            }
        }
        is ApiResult.Loading -> {
            val isLoading = event.isLoading
            if(isLoading){
                apiState.value = apiState.value.copy(isLoading = true, error = Throwable())
            }
        }
        is ApiResult.Success -> {
            val result = event.data
            apiState.value = apiState.value.copy(isLoading = false)
            onSuccess(result)
        }
    }
    return apiState.value
}
