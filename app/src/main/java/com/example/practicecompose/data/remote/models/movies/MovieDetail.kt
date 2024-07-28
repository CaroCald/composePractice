package com.example.practicecompose.data.remote.models.movies

import com.example.practicecompose.domain.entities.generics.api.GenericResponse
import com.google.gson.annotations.SerializedName

class MovieDetail(
    code: Int? = null,
    responseType: String? = null,
    message: String? = null,
    content: MovieDetailContent? = null
) : GenericResponse<MovieDetailContent>(code, responseType, message, content)

data class MovieDetailContent (
    val adult: Boolean = false,

    @SerializedName("backdrop_path")
    val backdropPath: String = "",

    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection = BelongsToCollection(),

    val budget: Long = 0L,
    val genres: List<Genre> = emptyList(),
    val homepage: String = "",
    val id: Long = 0L,

    @SerializedName("imdb_id")
    val imdbID: String = "",

    @SerializedName("origin_country")
    val originCountry: List<String> = emptyList(),

    @SerializedName("original_language")
    val originalLanguage: String = "",

    @SerializedName("original_title")
    val originalTitle: String = "",

    val overview: String = "",
    val popularity: Double = 0.0,

    @SerializedName("poster_path")
    val posterPath: String = "",

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany> = emptyList(),

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry> = emptyList(),

    @SerializedName("release_date")
    val releaseDate: String = "",

    val revenue: Long = 0L,
    val runtime: Long = 0L,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = emptyList(),

    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    val voteCount: Long = 0L
)


data class BelongsToCollection (
    val id: Long = 0L,
    val name: String = "",

    @SerializedName("poster_path")
    val posterPath: String = "",

    @SerializedName("backdrop_path")
    val backdropPath: String = ""
)


data class Genre (
    val id: Long = 0L,
    val name: String = ""
)


data class ProductionCompany (
    val id: Long = 0L,

    @SerializedName("logo_path")
    val logoPath: String = "",

    val name: String = "",

    @SerializedName("origin_country")
    val originCountry: String = ""
)


data class ProductionCountry (
    @SerializedName("iso_3166_1")
    val iso3166_1: String = "",

    val name: String = ""
)


data class SpokenLanguage (
    @SerializedName("english_name")
    val englishName: String = "",

    @SerializedName("iso_639_1")
    val iso639_1: String = "",

    val name: String = ""
)
