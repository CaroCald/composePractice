package com.example.practicecompose.data.remote.services

import com.example.practicecompose.data.remote.models.movies.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("now_playing")
    suspend fun fetchMovies(): Response<MovieResponse>
}