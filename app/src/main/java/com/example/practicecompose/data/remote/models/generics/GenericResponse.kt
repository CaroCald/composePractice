package com.example.practicecompose.data.remote.models.generics

open class GenericResponse<T>(
    val code: Int? = null,
    val responseType: String? = null,
    val message: String? = null,
    val content: T? = null
)