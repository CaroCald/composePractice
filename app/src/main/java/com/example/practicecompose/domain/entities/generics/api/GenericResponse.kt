package com.example.practicecompose.domain.entities.generics.api

import com.google.gson.annotations.SerializedName

open class GenericResponse<T>(
    @SerializedName("status_code")
    val code: Int? = null,
    val responseType: String? = null,
    @SerializedName("status_message")
    val message: String? = null,
    val content: T? = null
)