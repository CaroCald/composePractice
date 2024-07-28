package com.example.practicecompose.domain.entities.generics.api

data class GenericApiState(
    var isLoading: Boolean = false,
    var error: Throwable? = null
)