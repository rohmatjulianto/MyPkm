package com.joule.mypoke.ui.detail

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

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    init {
        getDetailPokemon(name)
    }

    fun getDetailPokemon(name: String) {
        viewModelScope.launch(dispatcher) {
            val result = repo.getPokemon(name)
            when (result) {
                is Resource.Success -> {
                    _pokemon.postValue(result.data!!)
                    validateFromDb(result.data.id)
                }
                is Resource.Error -> {
                    _errorMsg.postValue(result.msg!!)
                }
            }
        }
    }

    fun validateFromDb(id: Int) {
        viewModelScope.launch(dispatcher) {
            val result = pokeDao.getById(id)
            if (result != null) {
                _favoriteState.emit(FavoriteState.OnNull)
            } else {
                _favoriteState.emit(FavoriteState.OnSaved)
            }
        }
    }

    fun saveOrDeleteFromFavorite(id: Int, name: String) {
        viewModelScope.launch(dispatcher) {
            val result = pokeDao.getById(id)
            if (result != null) {
                pokeDao.delete(PokeEntity(id, name))
                _favoriteState.emit(FavoriteState.OnDeleted)
            } else {
                pokeDao.insert(PokeEntity(id, name))
                _favoriteState.emit(FavoriteState.OnSaved)
            }
        }
    }

}