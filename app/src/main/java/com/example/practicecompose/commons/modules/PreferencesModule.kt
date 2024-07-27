package com.example.practicecompose.commons.modules


import android.content.Context
import com.example.practicecompose.data.local.managers.SessionManager
import com.example.practicecompose.data.local.preferences.EncryptedPreferencesManager
import com.example.practicecompose.data.remote.impl.AccountServiceImpl
import com.example.practicecompose.data.remote.services.AccountService
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Provides
    @Singleton
    fun providePreferencesManager(
        @ApplicationContext context: Context
    ): EncryptedPreferencesManager {
     return EncryptedPreferencesManager(context)
    }
    @Provides
    @Singleton
    fun provideSessionManager(encryptedPreferencesManager: EncryptedPreferencesManager): SessionManager {
        return SessionManager(encryptedPreferencesManager)
    }
}
