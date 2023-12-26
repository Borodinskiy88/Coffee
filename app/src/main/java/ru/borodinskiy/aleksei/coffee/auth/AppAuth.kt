package ru.borodinskiy.aleksei.coffee.auth

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.borodinskiy.aleksei.coffee.model.AuthModel
import ru.borodinskiy.aleksei.coffee.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppAuth @Inject constructor(
    @ApplicationContext
    private val context: Context,
) {
    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    private val TOKENLIFETIME_KEY = "TOKENLIFETIME_KEY"
    private val TOKEN_KEY = "TOKEN_KEY"

    private val _data: MutableStateFlow<AuthModel?>

    init {
        val token = prefs.getString(TOKEN_KEY, null)
        val tokenLifetime = prefs.getInt(TOKENLIFETIME_KEY, 0)

        if (token == null || tokenLifetime == 0) {
            _data = MutableStateFlow(null)

            prefs.edit { clear() }
        } else {
            _data = MutableStateFlow(AuthModel(tokenLifetime, token))
        }
    }

    val data = _data.asStateFlow()

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface AppAuthEntryPoint {
        fun apiService(): ApiService
    }

    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }

    fun getTokenLifetime(): Int {
        return prefs.getInt(TOKENLIFETIME_KEY, 0)
    }

    @Synchronized
    fun setAuth(tokenLifetime: Int, token: String) {
        _data.value = AuthModel(tokenLifetime, token)
        prefs.edit {
            putInt(TOKENLIFETIME_KEY, tokenLifetime)
            putString(TOKEN_KEY, token)
        }
    }

    @Synchronized
    fun removeAuth() {
        _data.value = null
        prefs.edit { clear() }
    }
}