package ru.borodinskiy.aleksei.coffee.model

data class AuthModel(
    val tokenLifetime: Int,
    val token: String
)