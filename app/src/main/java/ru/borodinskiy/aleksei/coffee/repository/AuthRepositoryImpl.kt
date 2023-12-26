package ru.borodinskiy.aleksei.coffee.repository

import ru.borodinskiy.aleksei.coffee.api.ApiService
import ru.borodinskiy.aleksei.coffee.dto.User
import ru.borodinskiy.aleksei.coffee.error.ApiError
import ru.borodinskiy.aleksei.coffee.error.NetworkError
import ru.borodinskiy.aleksei.coffee.model.AuthModel
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {

    override suspend fun login(user: User): AuthModel {
        try {
            val response = apiService.login(user)

            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }

            val body = response.body() ?: throw ApiError(response.code(), response.message())
            return AuthModel(body.tokenLifetime, body.token)

        } catch (e: ApiError) {
            throw ApiError(e.status, e.code)
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError()
        }
    }

    override suspend fun register(user: User): AuthModel {
        try {
            val response = apiService.register(user)

            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }

            val body = response.body() ?: throw ApiError(response.code(), response.message())
            return AuthModel(body.tokenLifetime, body.token)

        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError()
        }
    }


}