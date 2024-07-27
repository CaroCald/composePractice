package com.example.practicecompose.features.login

import com.example.practicecompose.domain.entities.UiText

data class LoginState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
)
