package ru.borodinskiy.aleksei.coffee.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.borodinskiy.aleksei.coffee.api.ApiService
import ru.borodinskiy.aleksei.coffee.dto.Menu
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val apiService: ApiService
)  {

    fun getMenu(id: Int): Flow<Menu> = flow {
        val response = apiService.getMenu(id)
        emit(response)
    }.flowOn(Dispatchers.IO)

}