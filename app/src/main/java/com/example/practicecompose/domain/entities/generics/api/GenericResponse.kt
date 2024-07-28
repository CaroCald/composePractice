package com.example.practicecompose.domain.entities.generics.api

open class GenericResponse<T>(
    val code: Int? = null,
    val responseType: String? = null,
    val message: String? = null,
    val content: T? = null
)