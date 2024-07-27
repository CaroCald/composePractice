package com.example.practicecompose.domain.use_cases.forms

import com.example.practicecompose.R
import com.example.practicecompose.domain.entities.UiText
import com.example.practicecompose.domain.entities.ValidationResult

class ValidatePasswordUseCase : BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToConsistOfAtLeastEightCharacters),
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.any { it.isDigit() } &&
                password.any { it.isLetter() }
    }
}