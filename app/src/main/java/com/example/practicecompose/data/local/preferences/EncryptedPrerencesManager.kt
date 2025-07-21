package com.example.practicecompose.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.nio.charset.Charset
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import androidx.core.content.edit

class EncryptedPreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        EncryptedPreferencesKey.FILE_NAME, Context.MODE_PRIVATE
    )
    private val keyAlias = "encrypted_prefs_key"
    private val keyStoreType = "AndroidKeyStore"
    private val transformation = "AES/GCM/NoPadding"
    private val ivSize = 12 // 96 bits for GCM
    private val tagSize = 128

    private fun getOrCreateSecretKey(): SecretKey {
        val keyStore = KeyStore.getInstance(keyStoreType).apply { load(null) }
        val existingKey = keyStore.getEntry(keyAlias, null) as? KeyStore.SecretKeyEntry
        if (existingKey != null) return existingKey.secretKey
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, keyStoreType)
        val spec = KeyGenParameterSpec.Builder(
            keyAlias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()
        keyGenerator.init(spec)
        return keyGenerator.generateKey()
    }

    private fun encrypt(plainText: String): String {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, getOrCreateSecretKey())
        val iv = cipher.iv
        val encrypted = cipher.doFinal(plainText.toByteArray(Charset.forName("UTF-8")))
        val combined = ByteArray(iv.size + encrypted.size)
        System.arraycopy(iv, 0, combined, 0, iv.size)
        System.arraycopy(encrypted, 0, combined, iv.size, encrypted.size)
        return Base64.encodeToString(combined, Base64.DEFAULT)
    }

    private fun decrypt(cipherText: String): String? {
        val combined = Base64.decode(cipherText, Base64.DEFAULT)
        val iv = combined.copyOfRange(0, ivSize)
        val encrypted = combined.copyOfRange(ivSize, combined.size)
        val cipher = Cipher.getInstance(transformation)
        val spec = GCMParameterSpec(tagSize, iv)
        cipher.init(Cipher.DECRYPT_MODE, getOrCreateSecretKey(), spec)
        val decrypted = cipher.doFinal(encrypted)
        return String(decrypted, Charset.forName("UTF-8"))
    }

    fun putString(key: String, value: String) {
        prefs.edit { putString(key, encrypt(value)) }
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        val encrypted = prefs.getString(key, null) ?: return defaultValue
        return try { decrypt(encrypted) } catch (e: Exception) { defaultValue }
    }

    fun putInt(key: String, value: Int) = putString(key, value.toString())
    fun getInt(key: String, defaultValue: Int = 0): Int = getString(key)?.toIntOrNull() ?: defaultValue
    fun putBoolean(key: String, value: Boolean) = putString(key, value.toString())
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean = getString(key)?.toBoolean() ?: defaultValue
    fun remove(key: String) { prefs.edit { remove(key) } }
    fun clear() { prefs.edit { clear() } }
}
