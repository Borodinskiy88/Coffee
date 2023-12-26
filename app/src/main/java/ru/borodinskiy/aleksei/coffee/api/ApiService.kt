package ru.borodinskiy.aleksei.coffee.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import ru.borodinskiy.aleksei.coffee.dto.Shops
import ru.borodinskiy.aleksei.coffee.dto.Menu
import ru.borodinskiy.aleksei.coffee.dto.User
import ru.borodinskiy.aleksei.coffee.model.AuthModel

interface ApiService {

    //auth
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("auth/login")
    suspend fun login(
        @Body user: User
    ): Response<AuthModel>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("auth/register")
    suspend fun register(
    @Body user: User
    ): Response<AuthModel>

    @GET("location/{id}/menu")
    suspend fun getMenu(
        @Path("id") id: Int,
    ): Menu

    @GET("location")
    suspend fun getShops(): Shops
}