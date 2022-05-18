package com.joule.mypoke.ui.home

import androidx.lifecycle.*
import com.joule.mypoke.commons.Extensions.getIdFromUrl
import com.joule.mypoke.domain.PokeRepository
import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.local.PokeEntity
import com.joule.mypoke.model.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    val repo: PokeRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _pokemon = MutableLiveData<Resource<List<PokeEntity>>>()
    val pokemon: LiveData<Resource<List<PokeEntity>>> = _pokemon

    fun getAllPokemon(offest: Int) {
        viewModelScope.launch(dispatcher) {
            val result = repo.getAllPokemon(offest)
            _pokemon.postValue(result)
        }

    }

    fun searchAllPokemon(value: String) {
        viewModelScope.launch(dispatcher) {
            val result = repo.searchAllPokemon(value)
            _pokemon.postValue(result)
        }
    }
}