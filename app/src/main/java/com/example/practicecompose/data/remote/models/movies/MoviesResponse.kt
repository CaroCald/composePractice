package com.example.practicecompose.data.remote.models.movies

import com.google.gson.annotations.SerializedName


data class MovieResponse (
    val dates: Dates,
    val page: Long,
    val results: List<Result>,
    val totalPages: Long,
    val totalResults: Long
)

data class Dates (
    val maximum: String,
    val minimum: String
)

data class Result (
    val adult: Boolean,
    val backdropPath: String,
    val genreIDS: List<Long>,
    val id: Long,
    val originalLanguage: OriginalLanguage,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)

enum class OriginalLanguage {
    En,
    Es,
    Fr
}
