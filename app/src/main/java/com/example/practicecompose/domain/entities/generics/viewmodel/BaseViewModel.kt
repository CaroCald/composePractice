package com.example.practicecompose.domain.entities.generics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    protected val context
        get() = getApplication<Application>()

    open fun restoreState() {}
}