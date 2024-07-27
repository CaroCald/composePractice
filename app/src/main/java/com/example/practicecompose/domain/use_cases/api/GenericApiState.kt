package com.example.practicecompose.domain.use_cases.api

data class GenericApiState(
    var isLoading: Boolean = false,
    var error: Throwable? = null
)