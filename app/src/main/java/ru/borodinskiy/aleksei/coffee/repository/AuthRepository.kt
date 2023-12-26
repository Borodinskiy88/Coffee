package ru.borodinskiy.aleksei.coffee.repository

import ru.borodinskiy.aleksei.coffee.dto.User
import ru.borodinskiy.aleksei.coffee.model.AuthModel

interface AuthRepository {
    suspend fun login(user: User): AuthModel
    suspend fun register(user: User): AuthModel

}