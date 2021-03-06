package com.joule.mypoke.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.local.PokeEntity
import com.joule.mypoke.model.Pokemon
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(val pokeDao: PokeDao, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val _pokemon = MutableLiveData<List<PokeEntity>>()
    val pokemon : LiveData<List<PokeEntity>> = _pokemon

    init {
        getAllPokemon()
    }

    fun getAllPokemon(){
        viewModelScope.launch(dispatcher) {
            val result = pokeDao.getAllPokemonFavorite()
            if (result.isNotEmpty()){
                _pokemon.postValue(result)
            }
        }
    }
}