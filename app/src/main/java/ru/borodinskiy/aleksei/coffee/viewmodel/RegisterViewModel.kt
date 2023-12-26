package ru.borodinskiy.aleksei.coffee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.borodinskiy.aleksei.coffee.auth.AppAuth
import ru.borodinskiy.aleksei.coffee.dto.User
import ru.borodinskiy.aleksei.coffee.model.AuthModel
import ru.borodinskiy.aleksei.coffee.model.AuthModelState
import ru.borodinskiy.aleksei.coffee.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val appAuth: AppAuth
) : ViewModel() {

    val data: LiveData<AuthModel?> = appAuth
        .data
        .asLiveData(Dispatchers.Default)


    private val _state = MutableLiveData<AuthModelState>()
    val state: LiveData<AuthModelState>
        get() = _state


    fun register(user: User) = viewModelScope.launch {
        if (user.login.isNotBlank() && user.password.isNotBlank()) {

                try {
                    _state.value = AuthModelState(loading = true)
                    val result = repository.register(user)
                    appAuth.setAuth(result.tokenLifetime, result.token)
                    _state.value = AuthModelState(successfulEntry = true)
                } catch (e: Exception) {
                    _state.value = AuthModelState(error = true)
                }

        } else {
            _state.value = AuthModelState(isBlank = true)
        }
        _state.value = AuthModelState()

    }
}