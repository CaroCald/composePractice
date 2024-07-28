package com.example.practicecompose.data.remote.services

import com.example.practicecompose.data.remote.models.movies.MovieContent
import com.example.practicecompose.data.remote.models.movies.MovieDetail
import com.example.practicecompose.data.remote.models.movies.MovieDetailContent
import com.example.practicecompose.data.remote.models.movies.MovieResponse
import com.example.practicecompose.navigation.NavigationItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("now_playing")
    suspend fun fetchMovies(): Response<MovieContent>

    @GET("{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movieId: String,
        @Query("language") language: String = "en-US"
    ): Response<MovieDetailContent>}