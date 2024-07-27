package com.example.practicecompose.domain.use_cases.forms

interface BaseUseCase<T, ValidationResult> {
    fun execute(input: String):ValidationResult
}
