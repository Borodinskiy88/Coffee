package ru.borodinskiy.aleksei.coffee.repository

import ru.borodinskiy.aleksei.coffee.dto.User
import ru.borodinskiy.aleksei.coffee.model.AuthModel

interface AuthRepository {
    suspend fun login(login: String, password: String): AuthModel
    suspend fun register(login: String, password: String): AuthModel

}