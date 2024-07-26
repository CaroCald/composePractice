package com.example.practicecompose.data.remote.impl

import com.example.practicecompose.data.remote.models.movies.MovieResponse
import com.example.practicecompose.data.remote.services.MovieService
import retrofit2.Response

class  MovieServiceImpl : MovieService {


    override suspend fun fetchMovies(): Response<MovieResponse> {
        TODO("Not yet implemented")
    }

}