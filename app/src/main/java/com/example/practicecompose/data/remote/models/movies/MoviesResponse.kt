package com.example.practicecompose.data.remote.models.movies
import com.example.practicecompose.domain.entities.generics.api.GenericResponse
import com.google.gson.annotations.SerializedName

class MovieResponse(
    code: Int? = null,
    responseType: String? = null,
    message: String? = null,
    content: MovieContent? = null
) : GenericResponse<MovieContent>(code, responseType, message, content)

data class MovieContent(
    val dates: Dates? = null,
    val page: Long? = null,
    val results: List<Result>? = null,
    val totalPages: Long? = null,
    val totalResults: Long? = null
)

data class Dates(
    val maximum: String? = null,
    val minimum: String? = null
)

data class Result(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genreIDS: List<Long>? = null,
    val id: Long? = null,
    val originalLanguage: OriginalLanguage? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Long? = null
)

enum class OriginalLanguage {
    En,
    Es,
    Fr
}
