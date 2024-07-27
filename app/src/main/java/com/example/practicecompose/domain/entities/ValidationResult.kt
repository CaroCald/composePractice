package com.example.practicecompose.domain.entities

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)