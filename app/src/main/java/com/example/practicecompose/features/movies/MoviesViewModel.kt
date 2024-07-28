package com.example.practicecompose.features.movies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import baseEventApi
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.movies.MovieDetail
import com.example.practicecompose.data.remote.models.movies.MovieResponse
import com.example.practicecompose.data.remote.repositories.MovieRepository
import com.example.practicecompose.domain.entities.generics.api.GenericApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository,
): ViewModel(){
    private val _movieState = MutableStateFlow<ApiResult<MovieResponse>>(ApiResult.Loading(false))
    private val _movieDetail= MutableStateFlow<ApiResult<MovieDetail>>(ApiResult.Loading(false))

    val movieState: StateFlow<ApiResult<MovieResponse>> = _movieState.asStateFlow()
    val movieDetailState: StateFlow<ApiResult<MovieDetail>> = _movieDetail.asStateFlow()

    var apiState by mutableStateOf(GenericApiState())

    @Composable
    fun EventApi(onSuccess: @Composable () -> Unit, onError: () -> Unit){
        val event by movieState.collectAsState()
        apiState = baseEventApi(event =event , onSuccess = {
            onSuccess()
        }, onError = {
            onError()
        })
    }

    @Composable
    fun EventApiDetail(onSuccess: @Composable () -> Unit, onError: () -> Unit){
        val event by movieDetailState.collectAsState()
        apiState = baseEventApi(event =event , onSuccess = {
            onSuccess()
        }, onError = {
            onError()
        })
    }

    fun fetchMovies() {
        viewModelScope.launch {
            repository.fetchMovies()
                .collect { result ->
                    _movieState.value = result
                }
        }
    }

    fun getMovieDetail(id:String) {
        viewModelScope.launch {
            repository.movieById(id)
                .collect { result ->
                    _movieDetail.value = result

                }
        }
    }

}