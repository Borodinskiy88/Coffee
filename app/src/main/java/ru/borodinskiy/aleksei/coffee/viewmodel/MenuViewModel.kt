package ru.borodinskiy.aleksei.coffee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.borodinskiy.aleksei.coffee.dto.Menu
import ru.borodinskiy.aleksei.coffee.repository.MenuRepository
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: MenuRepository
) : ViewModel() {

    fun getMenu(id: Int): LiveData<Menu> = repository.getMenu(id).asLiveData()
}