package com.example.practicecompose.domain.use_cases.forms

import com.example.practicecompose.R
import com.example.practicecompose.domain.entities.generics.forms.UiText
import com.example.practicecompose.domain.entities.generics.forms.ValidationResult

class ValidatePasswordUseCase : BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToConsistOfAtLeastEightCharacters)
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}