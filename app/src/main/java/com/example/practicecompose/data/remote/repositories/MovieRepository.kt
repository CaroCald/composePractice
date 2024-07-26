package com.example.practicecompose.data.remote.repositories

import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.CustomError
import com.example.practicecompose.data.remote.models.movies.MovieResponse
import com.example.practicecompose.data.remote.services.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val service: MovieService
) {
    fun fetchMovies(): Flow<ApiResult<MovieResponse>> = flow {
        try {
            emit(ApiResult.Loading(true))
            val response = service.fetchMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ApiResult.Success(it))
                } ?: emit(ApiResult.Error(CustomError.ServerError(message = response.message(), code = response.code())))
            } else {
                emit(ApiResult.Error(CustomError.ServerError(message = response.message(), code = response.code())))
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(e))
        }
    }


}