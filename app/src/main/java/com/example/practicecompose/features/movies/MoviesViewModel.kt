package com.example.practicecompose.features.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.movies.MovieResponse
import com.example.practicecompose.data.remote.models.movies.Result
import com.example.practicecompose.data.remote.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository,
): ViewModel(){
    private val _movieState = MutableStateFlow<ApiResult<MovieResponse>>(ApiResult.Loading(false))
    private val _movieListState =  MutableStateFlow(emptyList<Result>())

    val movieState: StateFlow<ApiResult<MovieResponse>> = _movieState.asStateFlow()

    fun fetchMovies() {
        viewModelScope.launch {
            repository.fetchMovies()
                .collect { result ->
                    _movieState.value = result

                }
        }
    }

}