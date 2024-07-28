package com.example.practicecompose.data.remote.repositories

import android.content.Context
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.movies.MovieContent
import com.example.practicecompose.data.remote.models.movies.MovieDetailContent
import com.example.practicecompose.data.remote.services.MovieService
import com.example.practicecompose.domain.entities.generics.api.toResultFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val service: MovieService
) {
    fun fetchMovies(context: Context): Flow<ApiResult<MovieContent>>  {
        return toResultFlow(context=context) {
            service.fetchMovies()
        }
    }

    fun movieById(movieId: String, context: Context): Flow<ApiResult<MovieDetailContent>>  {
        return toResultFlow(context = context) {
            service.getDetail(movieId)
        }
    }
}