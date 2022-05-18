package com.joule.mypoke.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joule.mypoke.domain.PokeRepository
import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.local.PokeEntity
import com.joule.mypoke.model.Pokemon
import com.joule.mypoke.model.Resource
import com.joule.mypoke.ui.favorite.FavoriteState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    name: String,
    val pokeDao: PokeDao,
    val repo: PokeRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _favoriteState = MutableStateFlow<FavoriteState>(FavoriteState.OnNull)
    val favoriteState = _favoriteState.asStateFlow()

    private val _pokemon = MutableLiveData<Resource<Pokemon>>()
    val pokemon: LiveData<Resource<Pokemon>> = _pokemon

    init {
        getDetailPokemon(name)
    }

    fun getDetailPokemon(name: String) {
        viewModelScope.launch(dispatcher) {
            val result = repo.getPokemon(name)
            _pokemon.postValue(result)
        }
    }

    fun validateFromDb(id: Int){
        viewModelScope.launch(dispatcher) {
            val result = pokeDao.getById(id)
            if (result?.isFav == 0) {
                _favoriteState.emit(FavoriteState.OnNull)
            } else {
                _favoriteState.emit(FavoriteState.OnSaved)
            }
        }
    }

    fun saveOrDeleteFromFavorite(id: Int, name: String) {
        viewModelScope.launch(dispatcher) {
            val result = pokeDao.getById(id)
            if (result?.isFav != 0) {
                pokeDao.updateFav(PokeEntity(id, name, 0))
                _favoriteState.emit(FavoriteState.OnDeleted)
            } else {
                pokeDao.updateFav(PokeEntity(id, name, 1))
                _favoriteState.emit(FavoriteState.OnSaved)
            }
        }
    }

}