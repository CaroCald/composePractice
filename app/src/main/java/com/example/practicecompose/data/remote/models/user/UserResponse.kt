package com.example.practicecompose.data.remote.models.user

import com.example.practicecompose.data.remote.models.generics.GenericResponse
import com.example.practicecompose.data.remote.models.generics.OkResponse

class UserResponse(
    code: Int? = null,
    responseType: String? = null,
    message: String? = null,
    content: OkResponse? = null
) : GenericResponse<OkResponse>(code, responseType, message, content)