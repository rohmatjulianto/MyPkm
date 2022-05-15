package com.joule.mypoke.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joule.mypoke.domain.PokeRepository
import com.joule.mypoke.model.Pokemon
import com.joule.mypoke.model.Resource
import kotlinx.coroutines.launch

class DetailViewModel( name: String, val repo: PokeRepository) : ViewModel() {

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon : LiveData<Pokemon> = _pokemon

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg : LiveData<String> = _errorMsg

    init {
        getDetailPokemon(name)
    }

    fun getDetailPokemon(name: String){
        viewModelScope.launch {
            val result = repo.getPokemon(name)
            when (result){
                is Resource.Success -> {
                    _pokemon.postValue(result.data!!)
                }
                is Resource.Error ->{
                    _errorMsg.postValue(result.msg!!)
                }
            }
        }

    }

}