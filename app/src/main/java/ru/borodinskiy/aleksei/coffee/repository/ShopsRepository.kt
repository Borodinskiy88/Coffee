package ru.borodinskiy.aleksei.coffee.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.borodinskiy.aleksei.coffee.api.ApiService
import ru.borodinskiy.aleksei.coffee.dto.Menu
import ru.borodinskiy.aleksei.coffee.dto.Shops
import javax.inject.Inject

class ShopsRepository @Inject constructor(
    private val apiService: ApiService
)  {

    fun getShops(): Flow<Shops> = flow {
        val response = apiService.getShops()
        emit(response)
    }.flowOn(Dispatchers.IO)

}