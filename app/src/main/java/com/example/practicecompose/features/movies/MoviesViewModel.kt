package com.example.practicecompose.features.movies

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.movies.MovieContent
import com.example.practicecompose.data.remote.models.movies.MovieDetailContent
import com.example.practicecompose.data.remote.models.movies.Result
import com.example.practicecompose.domain.entities.generics.api.GenericApiState
import com.example.practicecompose.domain.entities.generics.viewmodel.BaseViewModel
import com.example.practicecompose.domain.use_cases.api.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    application: Application
): BaseViewModel(application) {
    
    private val _movieState = MutableStateFlow<ApiResult<MovieContent>>(ApiResult.Loading(false))
    val movieState: StateFlow<ApiResult<MovieContent>> = _movieState.asStateFlow()
    
    private val _movieDetail = MutableStateFlow<ApiResult<MovieDetailContent>>(ApiResult.Loading(false))
    val movieDetailState: StateFlow<ApiResult<MovieDetailContent>> = _movieDetail.asStateFlow()

    var apiState by mutableStateOf(GenericApiState())

    fun updateApiState(newState: GenericApiState) {
        apiState = newState
    }

    fun getMoviesList(): List<Result>? {
        return when (val state = movieState.value) {
            is ApiResult.Success -> state.data.results
            else -> null
        }
    }

    fun getMovieDetail(): MovieDetailContent? {
        return when (val state = movieDetailState.value) {
            is ApiResult.Success -> state.data
            else -> null
        }
    }

    fun fetchMovies() {
        viewModelScope.launch {
            movieUseCase.fetchMovies(context)
                .collect { result ->
                    _movieState.value = result
                }
        }
    }

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            movieUseCase.getMovieDetail(id, context)
                .collect { result ->
                    _movieDetail.value = result
                }
        }
    }

    // Business logic methods using the use case
    fun getMoviesByGenre(genre: String): List<Result> {
        return movieUseCase.getMoviesByGenre(getMoviesList(), genre)
    }

    fun getMoviesSortedByRating(ascending: Boolean = false): List<Result> {
        return movieUseCase.getMoviesSortedByRating(getMoviesList(), ascending)
    }

    fun getMoviesSortedByDate(ascending: Boolean = false): List<Result> {
        return movieUseCase.getMoviesSortedByDate(getMoviesList(), ascending)
    }

    fun searchMoviesByTitle(query: String): List<Result> {
        return movieUseCase.searchMoviesByTitle(getMoviesList(), query)
    }

    fun getPopularMovies(threshold: Long = 1000): List<Result> {
        return movieUseCase.getPopularMovies(getMoviesList(), threshold)
    }

    override fun restoreState() {
        apiState = GenericApiState()
        _movieDetail.value = ApiResult.Error(Throwable())
        _movieState.value = ApiResult.Error(Throwable())
    }
}