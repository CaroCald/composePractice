package com.example.practicecompose.features.movies

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import baseEventApi
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.movies.MovieContent
import com.example.practicecompose.data.remote.models.movies.MovieDetailContent
import com.example.practicecompose.data.remote.models.movies.Result
import com.example.practicecompose.data.remote.repositories.MovieRepository
import com.example.practicecompose.domain.entities.generics.api.GenericApiState
import com.example.practicecompose.domain.entities.generics.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository,
     application: Application
): BaseViewModel(application) {
    private val _movieState = MutableStateFlow<ApiResult<MovieContent>>(ApiResult.Loading(false))
    private val _movieDetail= MutableStateFlow<ApiResult<MovieDetailContent>>(ApiResult.Loading(false))

    private var movieState: StateFlow<ApiResult<MovieContent>> = _movieState.asStateFlow()
    private var movieDetailState: StateFlow<ApiResult<MovieDetailContent>> = _movieDetail.asStateFlow()

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
    @Composable
    fun getResultSMovies(): List<Result>? {
        val moviesState by movieState.collectAsState()
        return (moviesState as ApiResult.Success).data.results
    }

    @Composable
    fun getDetailMovies(): MovieDetailContent {
        val movieState by movieDetailState.collectAsState()
        return  (movieState as ApiResult.Success).data
    }

    fun fetchMovies() {
        viewModelScope.launch {
            repository.fetchMovies(context)
                .collect { result ->
                    _movieState.value = result
                }
        }
    }

    fun getMovieDetail(id:String) {
        viewModelScope.launch {
            repository.movieById(id, context)
                .collect { result ->
                    _movieDetail.value = result

                }
        }
    }


    override fun restoreState() {
        apiState = GenericApiState()
        movieDetailState= MutableStateFlow<ApiResult<MovieDetailContent>>(ApiResult.Error(Throwable()))
        movieState= MutableStateFlow<ApiResult<MovieContent>>(ApiResult.Error(Throwable()))
    }
}