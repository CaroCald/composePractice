package com.example.practicecompose.data.local.managers

import com.example.practicecompose.data.local.preferences.EncryptedPreferencesKey
import com.example.practicecompose.data.local.preferences.EncryptedPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val encryptedPreferencesManager: EncryptedPreferencesManager
) {

    fun saveSession(isLogged: Boolean) {
        encryptedPreferencesManager.putBoolean(EncryptedPreferencesKey.IS_LOGGED, isLogged)
    }
    fun isSavedSession(): Boolean {
        return encryptedPreferencesManager.getBoolean(EncryptedPreferencesKey.IS_LOGGED)
    }

    fun clearSession() {
        encryptedPreferencesManager.clear()
    }
}
