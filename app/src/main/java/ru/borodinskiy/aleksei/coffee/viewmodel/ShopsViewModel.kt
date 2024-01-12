package ru.borodinskiy.aleksei.coffee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.borodinskiy.aleksei.coffee.dto.Shops
import ru.borodinskiy.aleksei.coffee.repository.ShopsRepository
import javax.inject.Inject

@HiltViewModel
class ShopsViewModel @Inject constructor(
    private val repository: ShopsRepository
) : ViewModel() {

    fun getShops(): LiveData<Shops> = repository.getShops().asLiveData()
}