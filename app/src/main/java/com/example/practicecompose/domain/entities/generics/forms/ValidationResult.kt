package com.example.practicecompose.domain.entities.generics.forms

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)