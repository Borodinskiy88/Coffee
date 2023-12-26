package ru.borodinskiy.aleksei.coffee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.borodinskiy.aleksei.coffee.api.ApiService
import ru.borodinskiy.aleksei.coffee.auth.AppAuth
import ru.borodinskiy.aleksei.coffee.dto.User
import ru.borodinskiy.aleksei.coffee.error.ApiError
import ru.borodinskiy.aleksei.coffee.model.AuthModelState
import ru.borodinskiy.aleksei.coffee.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val appAuth: AppAuth,
    private val apiService: ApiService
) : ViewModel() {

    private val _state = MutableLiveData<AuthModelState>()
    val state: LiveData<AuthModelState>
        get() = _state

    fun login(user: User) = viewModelScope.launch {
        if (user.login.isNotBlank() && user.password.isNotBlank()) {
            try {
                _state.value = AuthModelState(loading = true)
                val result = repository.login(user)
                appAuth.setAuth(result.tokenLifetime, result.token)
                _state.value = AuthModelState(successfulEntry = true)
            } catch (e: Exception) {
                val postsResponse = apiService.login(user)
                when (e) {
                    is ApiError -> if (postsResponse.code() == 404 || postsResponse.code() == 400) _state.value =
                        AuthModelState(invalidLoginOrPassword = true)

                    else ->
                        _state.value = AuthModelState(error = true)
                }
            }
        } else {
            _state.value = AuthModelState(isBlank = true)
        }
        _state.value = AuthModelState()
    }
}